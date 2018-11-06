package com.github.jonpereiradev.integrator.client.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractStatelessSecurityFilter extends GenericFilterBean {

    private static final Pattern AUTHORIZATION_PATTERN = Pattern.compile("Bearer (.+)");

    @Override
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authorizationHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null) {
            Matcher authorizationMatcher = AUTHORIZATION_PATTERN.matcher(authorizationHeader);

            if (authorizationMatcher.matches()) {
                DecodedJWT token = JWT.decode(authorizationMatcher.group(1));

                if (Calendar.getInstance().getTime().before(token.getExpiresAt())) {
                    authenticate(token);
                }
            }
        }

        chain.doFilter(request, response);
    }

    public abstract void authenticate(DecodedJWT token);

}
