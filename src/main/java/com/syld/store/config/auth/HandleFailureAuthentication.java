package com.syld.store.config.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

@Component
public class HandleFailureAuthentication implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println(exception.getLocalizedMessage());
        String errorCode = "error.authen.default";
        if (exception instanceof BadCredentialsException) {
            errorCode = "error.authen.wrong.password";
        } else if (exception instanceof DisabledException) {
            errorCode = "error.authen.account.disabled";
        } else {
            errorCode = exception.getMessage();
        }
        response.sendRedirect("/auth/login?error=" + errorCode);
    }
}
