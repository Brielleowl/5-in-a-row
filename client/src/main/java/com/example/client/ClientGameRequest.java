package com.example.client;


import java.util.UUID;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import com.example.game.GameContainer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientGameRequest {
    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target(path);
    Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
    Response response = invocationBuilder.get();
    
    public static String path = "http://localhost:8080";
    public ClientGameRequest(){
        this.client = ClientBuilder.newClient();
         webTarget = client.target(path);
    }
    public ClientGameRequest(WebTarget webTarget){

        this.webTarget = webTarget;
    }
    public GameContainer sendPosReq(UUID playerID,int col, int row, WebTarget webTarget){
        client = ClientBuilder.newClient();
        WebTarget curTarget = null;
        if(webTarget == null){
            curTarget = client.target(path);
        } else {
            curTarget = webTarget.path("/sendPos");
        }
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        GameContainer gc = new GameContainer();
        gc.setPlayerID(playerID);
        gc.setCol(col);
        gc.setRow(row);
        Response response = invocationBuilder.post(Entity.entity(gc, MediaType.APPLICATION_JSON));
        GameContainer responseData = response.readEntity(GameContainer.class);
        
        return responseData;
        
    }

    public void connectServer(UUID playerID, WebTarget webTarget){
        client = ClientBuilder.newClient();
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        GameContainer gc = new GameContainer();
        gc.setPlayerID(playerID);
        Response response = invocationBuilder.post(Entity.entity(gc,MediaType.APPLICATION_JSON));
    }

    public UUID isYourTurn(WebTarget webTarget){
        client = ClientBuilder.newClient();
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        UUID responseData = response.readEntity(UUID.class);
        
        System.out.println(responseData.toString());
        
        return responseData;
    }

    public boolean startReq(WebTarget webTarget){
        client = ClientBuilder.newClient();
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        String responseData = response.readEntity(String.class);
        if(responseData.equals("false")) return false;
        return true;

    }

    public boolean firstReq(UUID id, WebTarget webTarget){
        client = ClientBuilder.newClient();
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.post(Entity.entity(id,MediaType.APPLICATION_JSON));
        String responseData = response.readEntity(String.class);
        System.out.println(responseData);
        if(responseData.equals("false")) return false;
        return true;
    }

    public UUID winnerReq(WebTarget webTarget){
        client = ClientBuilder.newClient();
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.get();
        GameContainer responseData = response.readEntity(GameContainer.class);
        UUID winner = responseData.getWinnerID();
        return winner;
        
    }

    public char[][] gridReq(WebTarget webTarget){
        client = ClientBuilder.newClient();
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.get();
        GameContainer responseData = response.readEntity(GameContainer.class);
        char[][] grid = responseData.getGrid();
        return grid;
    }
}
