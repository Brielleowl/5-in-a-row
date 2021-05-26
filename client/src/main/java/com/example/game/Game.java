package com.example.game;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Game {
    public static char[][] grid;
    public Game(){
        grid = new char[6][9];
    }

    public static boolean isValidPos(int col, int row){
        if(col > 9 || col < 1) return false;
        if(row > 9 || row < 1) return false;
        if(grid[row-1][col-1] != '\0') return false;
        return true;
    }
}
