package com.memorynotfound.spring.security.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

public class TokenAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;

		// extract token from header
		final String accessToken = httpRequest.getHeader("authentication");
		if (null != accessToken) {
			// get and check whether token is valid ( from DB or file wherever you are
			// storing the token)

			// Populate SecurityContextHolder by fetching relevant information using token
			System.out.println("authentication token: " + accessToken);
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			GrantedAuthority auth1 = new GrantedAuthority() {
				private static final long serialVersionUID = -850909471146017456L;

				@Override
				public
				String getAuthority() {return "ROLE_ADMIN"; }
			};
			authorities.add(auth1);
			
			final User user = new User("username", "password", true, true, true, true, authorities); // TODO: get authorities
			final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
					null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		chain.doFilter(request, response);
	}

}