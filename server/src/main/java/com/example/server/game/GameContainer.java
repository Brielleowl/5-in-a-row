package com.example.server.game;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameContainer {
    int col;
    int row;
    boolean winned;
    boolean isTie;
    boolean changeTurn;
    UUID playerID;
    UUID winnerID;
    char[][] grid;
    String name;
    
    
    

}

