package br.com.solimar.jwt.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/echo")
@Produces({ MediaType.APPLICATION_JSON })
public class EchoEndpoint {

	// Test
	// http://localhost:8080/jjwt/service/echo
	@GET
	public Response echo() {
		return Response.ok().entity("serviço funcionando").build();
	}

	// Test
	// http://localhost:8080/jjwt/service/echo/jwt?message=abc
	@GET
	@Path("jwt")
	@JWTTokenNeeded(role={"ADMIN","DIR"})
	public Response echoWithJWTToken(@QueryParam("message") String message) {
		return Response.ok().entity(message == null ? "no message" : message).build();
	}
}
