package br.com.solimar.jwt.server;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class UserEndpoint {

	@GET
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticateUser(@HeaderParam("username") String login, @HeaderParam("password") String password) {
		System.out.println("entrou no serivço authenticateUser");

		try {

			// Authenticate the user using the credentials provided
			authenticate(login, password);

			// Issue a token for the user
			// String token = issueToken(login);
			String token = JWTSerice.issueToken(login);

			// Return the token on the response
			// return Response.ok().header(Status.AUTHORIZATION, "Bearer " +
			// token).build();
			return Response.status(200).entity(token).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

	private void authenticate(String loign, String pass) throws Exception {
		if (!pass.equals("123")) {
			throw new Exception("Senha não confere");
		}
	}

}
