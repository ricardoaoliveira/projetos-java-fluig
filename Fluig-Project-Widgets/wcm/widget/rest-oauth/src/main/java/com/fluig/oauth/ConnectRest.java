package com.fluig.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

@Path("/conn")
public class ConnectRest {

    // Estas KEYS deve ser cadastradas em uma OAuth Application do fluig
    public static final String FLUIG_CONSUMER_KEY = "f4ebc6ae-67a6-4f2d-ae25-6c70b99b4b7e";
    public static final String FLUIG_CONSUMER_SECRET = "71d3be2a-813d-4d55-9bd6-6971a388f152-275b2e2a-ea65-452c-885b-fd505397a996";

    private OAuthClient oAuthClient;
    private String domainProvider;

    private void config() {
        try {
            createConnect("http://mgwativosgestaoeadmi3732.fluig.cloudtotvs.com.br", "admin", "Mf4UEuac");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createConnect(String domain, String user, String password) throws MalformedURLException {
        domainProvider = domain;

        // Cria o client e atribui dominio e consumer keys
        //Não é necessario informar as urls de OAuth, pois esta usando as URLS do provider padrão.
        oAuthClient = new OAuthClient(domainProvider, FLUIG_CONSUMER_KEY, FLUIG_CONSUMER_SECRET);

        try {
            // O retorno da negociação e autenticação do usuário.
            LoginResult result = oAuthClient.prepareResources(user, password);
            HttpHelper.closeResource(result.getConnection());

            // Checa o código de retorno
            if (HttpHelper.returnSuccess(result.getConnection())) {
                System.out.println(result.getResponse());
                System.out.println("ACCESS TOKEN: " + oAuthClient.getToken());
                System.out.println("TOKEN SECRET: " + oAuthClient.getTokenSecret());
            } else {
                System.out.println("NOK");
            }
        } catch (OAuthException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getSearch() throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        try {
            config();
            // Exemplo de requisição POST realizando uma consulta no fluig
            URL url = new URL(domainProvider + "/api/public/search/advanced");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(Constants.REQUEST_METHOD_POST);
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            oAuthClient.sign(conn);

            String json = "{\"searchType\" : \"GLOBAL\","
                    + "\"pattern\":\"\","
                    + "\"ordering\":\"RELEVANT\","
                    + "\"limit\":\"15\","
                    + "\"offset\":\"0\","
                    + "\"contentSearch\":\"false\","
                    + "\"documentTypes\":[\"FILEDOCUMENT\"],"
                    + "\"folderToSearch\":\"0\"}";

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json);
            wr.flush();
            wr.close();

            conn.connect();

            Reader inputCreateUser = new BufferedReader(new InputStreamReader(conn.getInputStream(), Constants.UTF_8_ENCODE));
            String retCreateUser = "";
            for (int c = inputCreateUser.read(); c != -1; c = inputCreateUser.read()) {
                retCreateUser += (char) c;
            }
            int code = conn.getResponseCode();
            System.out.println(String.format("RESPONSE: %d - %s: data: %s", code, conn.getResponseMessage(), retCreateUser));

            HttpHelper.closeResource(conn);
            return Response.status(code).entity(retCreateUser).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
    }

    @GET
    @Path("/userInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUserInfo() throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        try {
            config();
            // Exemplo de requisição GET para buscar os dados do usuário
            URL urlProvisioningTenant = new URL(domainProvider + "/api/public/2.0/users/getCurrent");
            HttpURLConnection connUserInfo = (HttpURLConnection) urlProvisioningTenant.openConnection();
            connUserInfo.setRequestMethod(Constants.REQUEST_METHOD_GET);
            connUserInfo.setDoInput(true);
            connUserInfo.setDoOutput(true);

            // Autentica a conexão
            oAuthClient.sign(connUserInfo);
            connUserInfo.connect();

            Reader inputCreateUser = new BufferedReader(new InputStreamReader(connUserInfo.getInputStream(), Constants.UTF_8_ENCODE));
            String retCreateUser = "";
            for (int c = inputCreateUser.read(); c != -1; c = inputCreateUser.read()) {
                retCreateUser += (char) c;
            }
            int code = connUserInfo.getResponseCode();
            System.out.println(String.format("RESPONSE: %d - %s: data: %s", code, connUserInfo.getResponseMessage(), retCreateUser));

            HttpHelper.closeResource(connUserInfo);
            return Response.status(code).entity(retCreateUser).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
    }
}