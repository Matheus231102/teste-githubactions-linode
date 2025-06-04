package com.desenv.mavenproject1.resources;

import jakarta.json.Json;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import java.io.IOException;

/**
 *
 * @author 
 */
@Path("jakartaee10")
public class JakartaEE10Resource {
    
    @GET
    public Response ping() throws IOException{
        return Response
                .ok(Json.createObjectBuilder().add("teste", "1").build().toString())
                .build();
    }
}
