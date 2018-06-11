package com.fluig.rest;

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

import com.fluig.foundation.integration.idp.vo.IdentityQuickViewDataVO;
import com.fluig.foundation.integration.idp.vo.IdentityQuickViewModelVO;
import com.fluig.foundation.integration.idp.vo.quickview.Filter;
import com.fluig.foundation.integration.idp.vo.quickview.Header;
import com.fluig.foundation.integration.idp.vo.quickview.RelatedAction;

@Path("/teste_webservice/webservices")
public class TesteWebserviceRest  {

    private static Logger logger = LoggerFactory.getLogger(TesteWebserviceRest.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @GET
    @Path("/metodo")
    @Produces({MediaType.APPLICATION_JSON})
    public Response metodo() {

        List<String> data = new ArrayList<String>();
        data.add("valueA");
        data.add("valueB");
        data.add("valueC");
        data.add("valueD");

        return Response.ok(data).build();
    }

}
