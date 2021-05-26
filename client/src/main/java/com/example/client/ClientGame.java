package com.example.client;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

import com.example.game.GameManager;
import com.example.player.Player;
import com.example.game.Game;
import com.example.game.GameContainer;



@Getter
@Setter
@Slf4j
public class ClientGame {
    static GameManager gManager = new GameManager();
    final static int defaultValue = 0;
    static boolean isWin = false;

    public static void main(String[] args) throws IOException, InterruptedException{
        
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("please enter your name:");
        String name = br.readLine();
        gManager.setPlayer(new Player(name)); 
        Thread.sleep(500);
        //connect to client

        gManager.connectWithServer(gManager.getPlayer());
        while(!gManager.isStart()){
            Thread.sleep(1000);
            System.out.println("Please wait another player to connect");

        }
        System.out.println("Let's start five in row game!!!!!!!");
        Thread.sleep(500);
        gManager.setGame(new Game());


      
        gManager.setFirst(gManager.checkFirst(gManager.getPlayer().getUuid()));
        
        log.info("current ID" + gManager.getPlayer().getUuid().toString());
        while(true){
            if(isWin){
                
                runWinnedAction();
                break;
            }
            if(gManager.isFirst()){
                runAction();
                if(isWin){
                
                    runWinnedAction();
                    break;
                }
                waitAnotherPlayer();
            } else {
                waitAnotherPlayer();
                if(isWin){
                
                    runWinnedAction();
                    break;
                }
                runAction();
            }
        }
        
    }  
    public static int toInt(String s){
        if(s == null || s.length() == 0){
            return defaultValue;
        }
        try{
            return Integer.parseInt(s);
        }catch(Exception e){
            return defaultValue;
        }
    } 

    public static void runAction() throws InterruptedException, IOException{
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("It's your trun  " + gManager.getPlayer().getName());
            Thread.sleep(500);
            char[][] curGrid = gManager.getGridFromServer();
            gManager.printGrid(curGrid);
        
            System.out.println("please enter coloum(1-9):");
            int col = toInt(br.readLine());
            System.out.println("please enter row(1-6):");
            int row = toInt(br.readLine());

            while(!Game.isValidPos(col,row)){
                System.out.println("please enter valid number");
                System.out.println("please enter coloum(1-9):");
                col = toInt(br.readLine());
                System.out.println("please enter row(1-6):");
                row = toInt(br.readLine());
                Thread.sleep(500);
            }
            // send col and row to server
            
            GameContainer curGC =gManager.sendColRow(col, row); 
            if(curGC.isWinned()){
                isWin = true;
                gManager.setWinner(curGC.getWinnerID());
            }
            //print current board
            System.out.println("current board: ");
            gManager.printGrid(curGC.getGrid());         
    }

    public static void waitAnotherPlayer(){
        boolean runNext = false;
        UUID winnerID = gManager.checkWinner();
        //check if another player winned
        if(winnerID != null){
            runWinnedAction();
            isWin = true;
            gManager.setWinner(winnerID);
            return;
        }
        try{
            while(true){ 
                System.out.println("please wait another player..... ");
                Thread.sleep(2000);
                runNext = gManager.checkTurnStatus(gManager.getPlayer());
                
                if(runNext) break;
            }

            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }


    public static void runWinnedAction(){
        System.out.println("current player: " + gManager.getPlayer().getUuid().toString());

        if(gManager.getWinner() != null && gManager.getWinner().compareTo(gManager.getPlayer().getUuid()) == 0){
            System.out.println("WIINER: " + gManager.getWinner().toString());    
            System.out.println("Congraduation " + gManager.getPlayer().getName() + " You are the winner!!!");
        
        }  else {
            System.out.println("Sorry you lost");
            
        }

    }
}
