package com.fluig.sample;

import java.io.Serializable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fluig.sdk.web.FluigRest;

@Path("/fluig-mgw-service")
public class FluigMGWService extends FluigRest{

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name")
    public Response getName() {
        try {
        	Serializable s = new String();
            return super.buildSuccessResponse(s);
        } catch (Exception e) {
            return super.buildErrorResponse(e);
        }
    }
	
}
