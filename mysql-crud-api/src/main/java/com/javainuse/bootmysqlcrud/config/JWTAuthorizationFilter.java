package com.javainuse.bootmysqlcrud.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javainuse.bootmysqlcrud.wrapper.ResponseWrapper;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SignatureException;
import java.util.*;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final String HEADER = "Authorization";

    private final String PREFIX = "Bearer ";

    private final String SECRET = "yourBase64EncodedSecretKeyyourBase64EncodedSecretKeyyourBase64EncodedSecretKey";

    private static final String[] WHITE_LIST_URL = new String[] {
            "/api/v1/auth/**", "/v2/api-docs", "/v3/api-docs", "/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui", "/configuration/security", "/swagger-ui/**", "/webjars/**",
            "/swagger-ui.html", "/api/auth/**", "/api/test/**", "/authenticate", "/api/v1/**" };

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if (checkJWTToken(request, response)) {
                Claims claims = validateToken(request);
                if (!isTokenExpired(claims)) {
                    setUpSpringAuthentication(claims);
                } else {
                    sendErrorResponse(response, 401, "Token expirado.");
                    return;
                }
            } else {
                SecurityContextHolder.clearContext();
                boolean isWhiteListed = false;
                for (String url : WHITE_LIST_URL) {
                    if (request.getRequestURI().startsWith(url)) {
                        isWhiteListed = true;
                        break;
                    }
                }
            }
            chain.doFilter((ServletRequest)request, (ServletResponse)response);
        } catch (ExpiredJwtException e) {
            sendErrorResponse(response, 403, "Token expirado.");
            return;
        } catch (UnsupportedJwtException e) {
            response.setStatus(403);
            response.getWriter().write("JWT signature verification failed: " + e.getMessage());
            return;
        } catch (PrematureJwtException e) {
            response.setStatus(403);
            response.getWriter().write("JWT signature premature failed: " + e.getMessage());
            return;
        } catch (ClaimJwtException e) {
            response.setStatus(403);
            response.getWriter().write("JWT signature verification failed: " + e.getMessage());
            return;
        } catch (MalformedJwtException e) {
            sendErrorResponse(response, 401, "Token invalido.");
            return;
        }
    }

    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
        return (Claims)Jwts.parser().setSigningKey("yourBase64EncodedSecretKeyyourBase64EncodedSecretKeyyourBase64EncodedSecretKey".getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    private void setUpSpringAuthentication(Claims claims) {
        List<String> authorities = (List<String>)claims.get("authorities");
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, (Collection)authorities.stream().map(org.springframework.security.core.authority.SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication((Authentication)auth);
    }

    private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader("Authorization");
        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer "))
            return false;
        return true;
    }

    private boolean isTokenExpired(Claims claims) {
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        ResponseWrapper<Object> responseBody = new ResponseWrapper(message);
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write(convertObjectToJson(responseBody));
    }

    private String convertObjectToJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "{\"error\": \"Se produjo un error al procesar la respuesta.\"}";
        }
    }

    private boolean checkRoleAuthorization(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Set<String> allowedURIsAdmin = new HashSet<>();
        allowedURIsAdmin.add("/api/v1/user/update");
        allowedURIsAdmin.add("/api/v1/user/create");
        String profileApiPrefix = "/api/v1/profile/";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String requestURI = request.getRequestURI();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals("PROFILE_ADMIN") && (allowedURIsAdmin.contains(requestURI) || requestURI.startsWith(profileApiPrefix)))
                return true;
        }
        return false;
    }
}
