package com.ny3an6.restapi.security.filters;

import com.ny3an6.restapi.security.token.TokenAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TokenAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String token = request.getParameter("token");
        TokenAuthentication tokenAuthentication= new TokenAuthentication(token);
        if(token==null){
            tokenAuthentication.setAuthenticated(false);
        }else {
            SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);// помещаем обьект токенаутентикейшен в спринг и отправляем на provider
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
