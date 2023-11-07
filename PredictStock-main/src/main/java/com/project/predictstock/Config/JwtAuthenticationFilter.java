package com.project.predictstock.Config;

import com.project.predictstock.Entities.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
// to check token
public class    JwtAuthenticationFilter extends OncePerRequestFilter {

    private final  Jwtservice jwtservice;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getServletPath().endsWith("/api/user")) {
            filterChain.doFilter(request, response);
            return;
        }

            //get the authorization from header
            final String authHeader = request.getHeader("Authorization");

            final String jwt;

            final String userEmail;

            // verify if I get the authorization in header, and it, start with "Bearer "
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request,response);
                return;
            }

            // get the jwt token from the header starting from position 7 => removing "Bearer "
            jwt = authHeader.substring(7);

            // extract email from token to verify existing of the user
            userEmail = jwtservice.extractUsername(jwt);

            // verify if user is not authenticated
            if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication() == null){
                //Get userdetails from db
                User userDetails = (User) this.userDetailsService.loadUserByUsername(userEmail);


                //check if user is valid
                if(jwtservice.isTokenValid(jwt,userDetails)){
                    // create token
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            userDetails.getPassword(),
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    // update auth token
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            // pass the hand to the next filter
            filterChain.doFilter(request,response);

    }
}
