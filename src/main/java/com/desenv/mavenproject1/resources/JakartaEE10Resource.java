package com.desenv.mavenproject1.resources;

import jakarta.json.Json;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author 
 */
@Path("jakartaee10")
public class JakartaEE10Resource {
    
    @GET
    public Response ping() throws IOException{
        Properties props = new Properties();
        try (InputStream input = JakartaEE10Resource.class.getResourceAsStream("/project.properties")) {
            props.load(input);
            String version = props.getProperty("project.version", "não foi possível pegar a versão disponível");
            return Response
                    .status(Response.Status.OK)
                    .entity(Json.createObjectBuilder().add("project.version", version).build().toString())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Json.createObjectBuilder().add("exception", e.getMessage()))
                    .build();
        }
    }
}
