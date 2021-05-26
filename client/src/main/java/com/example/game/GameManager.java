package com.example.game;

import java.util.UUID;
import javax.ws.rs.client.WebTarget;
import com.example.client.ClientGameRequest;
import com.example.player.Player;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class GameManager {

    boolean isWin;
    boolean atBegining;
    boolean isFirst;
    String winnerName;
    Game game;
    Player player;
    UUID winner;
    
    ClientGameRequest clientReq;
    public GameManager(){
        this.game = new Game();
        clientReq = new ClientGameRequest();
    }
    public void printGrid(Game game){
        char left = '[';
        char right = ']';
        for(int i = 0; i < Game.grid.length; i++){
            for(int j = 0; j< Game.grid[0].length; j++){
                System.out.print(left);
                System.out.print(Game.grid[i][j]);
                System.out.print(right);
            }
            System.out.println();
        }
    }
    public void printGrid(char[][] grid){
        char left = '[';
        char right = ']';
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j< grid[0].length; j++){
                System.out.print(left);
                System.out.print(grid[i][j]);
                System.out.print(right);
            }
            System.out.println();
        }
    }


    public GameContainer sendColRow(int col, int row){
        WebTarget posTarget = clientReq.getWebTarget().path("/sendPos");
        GameContainer gc = clientReq.sendPosReq(player.getUuid(), col, row ,posTarget);
        return gc;
    }

    public void connectWithServer(Player player){
        WebTarget connectTarget = clientReq.getWebTarget().path("/connect");
        clientReq.connectServer(player.getUuid(),connectTarget);
        
    }

    public boolean checkTurnStatus(Player player){
        WebTarget statusTarget = clientReq.getWebTarget().path("/check");
        UUID currentID = clientReq.isYourTurn(statusTarget);
        System.out.println("CHECK TURN STATUS");
        if(currentID.compareTo(player.getUuid()) == 0)return true;
        return false;

    }

    public int[] receivePos(){
        int[] pos = new int[2];
        return pos;
    }

    public boolean isStart(){
        WebTarget sTarget = clientReq.getWebTarget().path("/start");
        boolean start = clientReq.startReq(sTarget);
        return start;
    }

    public boolean checkFirst(UUID id){
        WebTarget idTarget = clientReq.getWebTarget().path("/isFirst");
        boolean first = clientReq.firstReq(id, idTarget);
        return first;
    }

    public UUID checkWinner(){
        WebTarget winTarget = clientReq.getWebTarget().path("/win");
        UUID winner = clientReq.winnerReq(winTarget);
        
        return winner;
    }

    public char[][] getGridFromServer(){
        WebTarget gridTarget = clientReq.getWebTarget().path("/grid");
        char[][] grid = clientReq.gridReq(gridTarget);
        return grid;
    }







    
    
        
}

