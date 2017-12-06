package br.com.solimar.jwt.server;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/pessoa")
@Produces({ MediaType.APPLICATION_JSON })
public class PessoaEndpoint {

	// Test
	// http://localhost:8080/jjwt/service/echo
	@GET
	public Response echo() {
		return Response.ok().entity("serviço funcionando").build();
	}

	@GET
	@Path("/list")
	@JWTTokenNeeded(role = { "ADMIN", "DIR" })
	public Response getlistAll() {
		System.out.println("PessoaEndpoint: /pessoa/list");
		List<PessoaVO> list = DatabaseSimulator.getPeople();
		return Response.status(200).entity(list).build();

	}
}
