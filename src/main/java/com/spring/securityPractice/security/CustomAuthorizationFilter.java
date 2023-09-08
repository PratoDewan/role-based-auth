package com.spring.securityPractice.security;

import com.spring.securityPractice.SpringApplicationContext;
import com.spring.securityPractice.constants.AppConstants;
import com.spring.securityPractice.entity.RoleEntity;
import com.spring.securityPractice.entity.UserEntity;
import com.spring.securityPractice.repository.UserRepository;
import com.spring.securityPractice.service.UserService;
import com.spring.securityPractice.service.impl.MyUserDetails;
import com.spring.securityPractice.service.impl.UserServiceImpl;
import com.spring.securityPractice.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(AppConstants.HEADER_STRING);
        if(header==null||!header.startsWith(AppConstants.TOKEN_PREFIX)){
            filterChain.doFilter(request,response);
        }else {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(header);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request,response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String header) {
        if(header != null) {
            String token = header.replace(AppConstants.TOKEN_PREFIX, "");
            String user = JWTUtils.hasTokenExpired(token) ? null : JWTUtils.extractUser(token);
            if (user != null) {

                Set<RoleEntity> roles = fetchRolesForUser(user);

                List<GrantedAuthority> authorities = roles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName().toUpperCase()))
                        .collect(Collectors.toList());

                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }
            return null;
        }
        return null;
    }
    private Set<RoleEntity> fetchRolesForUser(String userEmail) {
        UserRepository userRepository = (UserRepository) SpringApplicationContext.getBean("userRepository");
        UserEntity userEntity = userRepository.findByEmail(userEmail).get();

        if (userEntity==null) {
            return Collections.emptySet();
        } else {
            return userEntity.getRoles();
        }
    }

}
