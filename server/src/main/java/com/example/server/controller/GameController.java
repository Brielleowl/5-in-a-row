package com.example.server.controller;


import java.util.UUID;

import javax.ws.rs.core.MediaType;

import com.example.server.game.Game;
import com.example.server.game.GameContainer;
import com.example.server.game.Player;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
public class GameController {
    

    Game game = new Game();

    int numDrop = 0;
    @ResponseBody
    @PostMapping(path="/sendPos", produces = MediaType.APPLICATION_JSON)
    public GameContainer dropDisc(@RequestBody GameContainer gc){

        

        int col = gc.getCol();
        int row = gc.getRow();
        if(game.timeOut(game.getGTimeQueue())){
            log.info("one of client is TIME OUT");
            Game.exitApplication();

        }
        game.updateTimeStamp(gc.getPlayerID());
        game.chooseAndDrop(gc.getPlayerID(), col, row);
        numDrop++;
        
        if(!game.isWinned() && game.checkWin(gc.getPlayerID(), col, row)){
                game.setWinned(true);
                gc.setWinnerID(gc.getPlayerID());
                gc.setWinned(true);
                game.setWinned(true);
                Player winner = new Player();
                winner.setId(gc.getPlayerID());
                game.setWinnePlayer(winner);
        }
        

        if(numDrop == 54){
            gc.setTie(true);
        }
        log.info("col:" + col);
        System.out.println("col:" + col);
        log.info("row:" + row);
        log.info("Does anyone winned??" + gc.isWinned());
        gc.setGrid(game.getGrid());

        return gc;
    }

    @PostMapping(path="/connect", produces = MediaType.APPLICATION_JSON)
    public void connectClient(@RequestBody GameContainer gc){
        game.updateTimeStamp(gc.getPlayerID());
        System.out.println("connect with one player");
        int curNum = game.getConnected();
        System.out.println(curNum);
        if(curNum == 0){
            Player player = new Player();
            player.setId(gc.getPlayerID());
            game.setFirstPlayer(player);
            
        } else{
            Player player = new Player();
            player.setId(gc.getPlayerID());
            game.setSecondPlayer(player);
        }
        curNum++;
        game.setConnected(curNum);
        System.out.println("Server currently connect to" + game.getConnected() + "players");
        
    } 

    @GetMapping(path="/check", produces = MediaType.APPLICATION_JSON)
    public UUID checkTurn(){
        System.out.println("checking the status....");
        System.out.println("num:" + numDrop);

        if(numDrop % 2 == 0){
            return game.getFirstPlayer().getId();
        }
        return game.getSecondPlayer().getId(); 
    }

    @GetMapping(path="/win", produces = MediaType.APPLICATION_JSON)
    public GameContainer checkWinner(){
        GameContainer gc = new GameContainer();

        if(game.isWinned()){
            gc.setWinned(true);
            gc.setWinnerID(game.getWinnePlayer().getId());
            return gc;
        }
        return gc;
    }

    

    @GetMapping(path="/start", produces = MediaType.APPLICATION_JSON)
    public boolean gameStart(){
        System.out.println("checking can start?");
        if(game.getConnected() == 2){
            return true;
        }
        return false;
    }

    @PostMapping(path = "/isFirst", produces = MediaType.APPLICATION_JSON)
    public boolean checkFirst(@RequestBody UUID curID){
        System.out.println("");
        if(curID.compareTo(game.getFirstPlayer().getId()) == 0) return true;
        
        return false;
    }


    @GetMapping(path = "/grid", produces = MediaType.APPLICATION_JSON)
    public GameContainer resGrid(){
        GameContainer gc = new GameContainer();
        gc.setGrid(game.getGrid());
        return gc;
    }




  
}
