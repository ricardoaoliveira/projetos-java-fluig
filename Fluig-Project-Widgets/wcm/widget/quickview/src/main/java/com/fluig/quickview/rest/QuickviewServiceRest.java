package com.fluig.quickview.rest;

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

@Path("/sample/quickview")
public class QuickviewServiceRest  {

    private static Logger logger = LoggerFactory.getLogger(QuickviewServiceRest.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @GET
    @Path("/{indicador}/sw")
    @Produces({MediaType.APPLICATION_JSON})
    public Response model(@HeaderParam("tenantId") String tenantId, @HeaderParam("userId") String userId, @HeaderParam("tokenId") String tokenId, @PathParam("indicador") String indicador) {

        IdentityQuickViewModelVO modelVO = new IdentityQuickViewModelVO();
        List<Header> colModel = new ArrayList<Header>();
        colModel.add(new Header("descA","labelA", "left", "50",true));
        colModel.add(new Header("descB","labelB", "left", "50",true));
        colModel.add(new Header("descC","labelC", "left", "50",true));
        Header header = new Header("descD","labelC", "left", "50",true);
        header.setHidden(true);
        colModel.add(header);
        modelVO.setColModel(colModel);

        List<Filter> filters = new ArrayList<Filter>();
        filters.add(new Filter("filterA", "filterCode"));
        filters.add(new Filter("filterB", "filterCode"));
        modelVO.setFilters(filters);

        List<RelatedAction> relatedActions = new ArrayList<RelatedAction>();
        relatedActions.add(new RelatedAction("actionA", "relayStateA"));
        relatedActions.add(new RelatedAction("actionB", "relayStateB"));
        modelVO.setRelatedActions(relatedActions);

        List<Filter> selectionValues = new ArrayList<Filter>();
        selectionValues.add(new Filter("valueA", "codeA"));
        selectionValues.add(new Filter("valueB", "codeB"));
        modelVO.setSelectionValues(selectionValues);

        return Response.ok(modelVO).build();
    }

    @GET
    @Path("/{indicador}")
    @Produces({MediaType.APPLICATION_JSON})
//    Header params (String tenantId, String userId, String tokenId)
//    Query params (String searchcol, String searchval, String selection, String search,
//            String nd,String rows,String page,String sortcol, String sortorder, String locale)
    public Response data(@HeaderParam("tenantId") String tenantId, @HeaderParam("userId") String userId,
            @HeaderParam("tokenId") String tokenId, @PathParam("indicador") String indicador,
            @QueryParam("searchcol") String searchcol, @QueryParam("searchval") String searchval,
            @QueryParam("selection") String selection, @QueryParam("search") String search, @QueryParam("nd") String nd,
            @QueryParam("rows") String rows, @QueryParam("page") String page, @QueryParam("sortcol") String sortcol,
            @QueryParam("sortorder") String sortorder, @QueryParam("locale") String locale) {

        IdentityQuickViewDataVO dataVO = new IdentityQuickViewDataVO();
        dataVO.setCurrpage(0);
        dataVO.setTotalpages(1);
        dataVO.setTotalrecords(1);

        List<Map<String,String>> data = new ArrayList<Map<String,String>>();
        Map<String,String> row = new HashMap<String,String>();
        row.put("descA", "descriptionA");
        row.put("descB", "descriptionB");
        row.put("descC", "descriptionC");
        row.put("descD", "descriptionD");
        data.add(row);
        dataVO.setData(data);

        return Response.ok(dataVO).build();
    }

    @GET
    @Path("/{indicador}/ac")
    @Produces({MediaType.APPLICATION_JSON})
//    Header params (String tenantId, String userId, String tokenId)
//    Query params (String searchcol, String searchval, String selection, String rows)
    public Response autocomplete(@HeaderParam("tenantId") String tenantId, @HeaderParam("userId") String userId,
            @HeaderParam("tokenId") String tokenId, @PathParam("indicador") String indicador,
            @QueryParam("searchcol") String searchcol, @QueryParam("searchval") String searchval,
            @QueryParam("selection") String selection, @QueryParam("rows") String rows) {

        List<String> data = new ArrayList<String>();
        data.add("valueA");
        data.add("valueB");
        data.add("valueC");
        data.add("valueD");

        return Response.ok(data).build();
    }

}
