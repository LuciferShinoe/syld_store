package com.syld.store.config.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

@Component
public class HandleSuccessAuthentication implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.sendRedirect("/home");
    }
}
