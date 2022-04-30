package com.birthdaynotifier.filter;

import com.birthdaynotifier.constant.Constants;
import com.birthdaynotifier.model.CustomUser;
import com.birthdaynotifier.service.CustomUserDetailsService;
import com.birthdaynotifier.utility.JWTUtility;
import com.birthdaynotifier.utility.Utility;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService userDetailsService;
    private final JWTUtility jwtUtility;

    public JWTFilter(CustomUserDetailsService userDetailsService, JWTUtility jwtUtility) {
        this.userDetailsService = userDetailsService;
        this.jwtUtility = jwtUtility;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        String authorization = httpServletRequest.getHeader(Constants.authorization);

        if(!Utility.checkIfNullOrEmpty(authorization) && authorization.startsWith(Constants.bearer))
            token = authorization.substring(Constants.seven);

        if(!Utility.checkIfNullOrEmpty(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
            String username = jwtUtility.getUsernameFromToken(token);
            CustomUser userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtUtility.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
