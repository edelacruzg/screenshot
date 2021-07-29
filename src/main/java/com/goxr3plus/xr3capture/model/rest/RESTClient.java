/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.goxr3plus.xr3capture.model.rest;

import main.java.com.goxr3plus.xr3capture.model.rest.models.CallDocumentsResponse;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import main.java.com.goxr3plus.xr3capture.model.rest.models.CallDocumentsRequest;
import main.java.com.goxr3plus.xr3capture.model.rest.models.ValidatingRequest;
import main.java.com.goxr3plus.xr3capture.model.rest.models.ValidatingResponse;
/**
 *
 * @author edelacruz
 */
public class RESTClient {
    //private static final String WS_URI = "https://pruebas.dimmerapp.com/Finsus/videocall/";
    private static final String WS_URI = "https://www.dimmerapp.com/Finsus/videocall/";
    
     public static CallDocumentsResponse consultaDocs(CallDocumentsRequest idAsociado) throws RuntimeException {
    	Client client = null;
    	try {
    		client = ClientBuilder.newClient();
    		WebTarget target = client.target(getBaseUri());
    		CallDocumentsResponse resp = target.path("consultadocs").request()
    				.post(Entity.entity(idAsociado, MediaType.APPLICATION_JSON), CallDocumentsResponse.class);
    		return resp;
    	} catch(RuntimeException e) {
    		throw e;
    	} finally { if(client != null) client.close(); }
    }
     
     public static ValidatingResponse validaFaceMatch(ValidatingRequest documents) throws RuntimeException {
    	Client client = null;
    	try {
    		client = ClientBuilder.newClient();
    		WebTarget target = client.target(getBaseUri());
    		ValidatingResponse resp = target.path("validafacematch").request()
    				.post(Entity.entity(documents, MediaType.APPLICATION_JSON), ValidatingResponse.class);
    		return resp;
    	} catch(RuntimeException e) {
    		throw e;
    	} finally { if(client != null) client.close(); }
    }
     
     private static URI getBaseUri() {
        return UriBuilder.fromUri(WS_URI).build();
    }
}
