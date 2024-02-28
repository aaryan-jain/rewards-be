package com.rewards.api.auth;

import com.rewards.api.User.UserService;
import com.rewards.api.auth.apikey.ApiKeyEntity;
import com.rewards.api.auth.apikey.ApiKeyService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired JwtHelper jwtHelper;
    @Autowired
    UserService userService;

    @Autowired
    ApiKeyService apiKeyService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        String client = null;
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            //looking good
            token = requestHeader.substring(7);
            try {

                client = this.jwtHelper.getClientSubjectFromToken(token);

            } catch (IllegalArgumentException e) {
                System.out.println("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                System.out.println("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                System.out.println("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Invalid Header Value !! ");
        }

        if (client != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            //fetch user detail from username
//            User userDetails = this.userService.findByClerkId(clerkId);
            String clientActual = client.split("=====")[0];
            List<ApiKeyEntity> apiKeyEntities = this.apiKeyService.findByClient(clientActual);
            Boolean validateToken = this.jwtHelper.validateTokenForClient(token, apiKeyEntities.get(0));
            if (validateToken) {

                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(apiKeyEntities, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);


            } else {
                System.out.println("Validation fails !!");
            }


        }

        filterChain.doFilter(request, response);
    }
}
