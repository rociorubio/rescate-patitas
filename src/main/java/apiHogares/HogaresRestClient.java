package apiHogares;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.*;

public class HogaresRestClient {
    private static final String REFUGUIO_DDS = "https://api.refugiosdds.com.ar/api";
    private static final String HOGARES = "hogares";
    private static final String APPLIJSON = "application/json";
    private static final String TOKEN = "Bearer 09NQ853uFKKB6EkJHyeJMAOn64rVc2h2IGLY3RbQNNAXbKoUOs9MBFiZyLXA";

    public ClientResponse getHogares(String numeroDePagina, Client client) {
        ClientResponse response = client.resource(REFUGUIO_DDS).path(HOGARES)
            .queryParam("offset", numeroDePagina)
            .header("accept", APPLIJSON)
            .header("Authorization", TOKEN)
            .accept(MediaType.APPLICATION_JSON)
            .get(ClientResponse.class);
        return response;
    }


}
