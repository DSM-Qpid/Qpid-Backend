package com.example.qpid.global.security.jwt;

import com.example.qpid.domain.user.domain.RefreshToken;
import com.example.qpid.domain.user.domain.repository.RefreshTokenRepository;
import com.example.qpid.domain.user.presentation.dto.response.TokenResponse;
import com.example.qpid.global.exception.ExpiredTokenException;
import com.example.qpid.global.exception.InvalidTokenException;
import com.example.qpid.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    private static final String ACCESS_KEY = "access_token";
    private static final String REFRESH_KEY = "refresh_token";

    public TokenResponse generateToken(String accountId) {
        String accessToken = generateToken(accountId, ACCESS_KEY, jwtProperties.getAccessExp());
        String refreshToken = generateToken(accountId, REFRESH_KEY, jwtProperties.getRefreshExp());

        refreshTokenRepository.save(RefreshToken.builder()
                .id(accountId)
                .token(refreshToken)
                .ttl(jwtProperties.getRefreshExp() * 1000)
                .build());

        return new TokenResponse(accessToken, refreshToken);
    }

    private String generateToken(String accountId, String type, Long exp) {
        return Jwts.builder()
                .setSubject(accountId)
                .setHeaderParam("typ", type)
                .claim("role", "USER")
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .setIssuedAt(new Date())
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(jwtProperties.getHeader());
        if (bearer != null
                && bearer.startsWith(jwtProperties.getPrefix())
                && bearer.length() > jwtProperties.getPrefix().length() + 1)
            return bearer.substring(jwtProperties.getPrefix().length() + 1);
        return null;
    }

    public Authentication authentication(String token) {
        Claims body = getJws(token).getBody();
        if (!isNotRefreshToken(token)) throw InvalidTokenException.EXCEPTION;

        UserDetails userDetails = getDetails(body);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean isNotRefreshToken(String token) {
        return !REFRESH_KEY.equals(getJws(token).getHeader().get("typ").toString());
    }

    private Jws<Claims> getJws(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private UserDetails getDetails(Claims body) {
        return authDetailsService.loadUserByUsername(body.getSubject());

    }
}
