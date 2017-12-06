package br.com.solimar.jwt.client;

import java.io.Serializable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ClienteRest implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SERVER_URI = "http://localhost:8080/jjwt/service";

	private static final String ENTRY_POINT_MSG = "/echo";
	private static final String ENTRY_POINT_USER = "/users";

	public void getMsg(String token) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(SERVER_URI + ENTRY_POINT_MSG + "/jwt");

		Response response = null;
		try {

			response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, token).get();

			System.out.println("Status: " + response.getStatus());
			System.out.println("Status Info: " + response.getStatusInfo());
			System.out.println("Resposta: " + response.readEntity(String.class));

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}

	}

	public String authenticate() {

		Client client = ClientBuilder.newClient();
		String uri = SERVER_URI + ENTRY_POINT_USER + "/login";
		WebTarget target = client.target(uri);

		System.out.println("URL " + uri);

		Response response = null;
		try {

			response = target.request(MediaType.APPLICATION_JSON).header("username", "user").header("password", "123")
					.get();

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("Status: " + response.getStatus());
		System.out.println("Status Info: " + response.getStatusInfo());

		if (response.getStatus() == 200) {
			return response.readEntity(String.class);
		} else {
			System.out.println("readEntity: " + response.readEntity(String.class));
			return null;
		}

	}

	public static void main(String[] args) {

		ClienteRest cliente = new ClienteRest();

		System.out.println("******** Authenticate **********************");
		String token = cliente.authenticate();

		/*try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

		System.out.println("******** List **********************");
		cliente.getMsg(token);

	}

}
