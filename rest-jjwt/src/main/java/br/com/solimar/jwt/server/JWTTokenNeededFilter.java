package br.com.solimar.jwt.server;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

	public void filter(ContainerRequestContext requestContext) throws IOException {

		System.out.println("Entrou no filter");

		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		if (authorizationHeader != null) {

			// Extract the token from the HTTP Authorization header
			// String token =
			// authorizationHeader.substring("Bearer".length()).trim();
			String token = authorizationHeader.toString();

			try {
				// Validate the token
				Jwt.validateJwt(token);
				System.out.println("#### valid token : " + token);

			} catch (Exception e) {
				// logger.severe("#### invalid token : " + token);
				System.out.println("#### invalid token : " + token);
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			}

		} else {
			System.out.println("#### invalid token");
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}
}