package com.fluig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/datatablesServiceRest")
public class DatatablesServiceRest  {

	private static Logger logger = LoggerFactory.getLogger(DatatablesServiceRest.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
    @GET
    @Path("/teste")
    @Produces({MediaType.APPLICATION_JSON})
    public Response teste() {

        List<String> data = new ArrayList<String>();
        data.add("valueA");
        data.add("valueB");
        data.add("valueC");
        data.add("valueD");

        return Response.ok(data).build();
    }
    
    @GET
    @Path("/nome/{nome}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response testeParametro(@PathParam("nome") String nome) {

        List<String> data = new ArrayList<String>();
        data.add(nome);
        
        return Response.ok(data).build();
    }

}