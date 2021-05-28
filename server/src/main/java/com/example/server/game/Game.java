package com.example.server.game;

import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;


import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
    char[][] grid;
    char disc;
    boolean curFirst;
    boolean winned = false;
    int connected = 0;
    Player firstPlayer;
    Player secondPlayer;
    Player winnePlayer;
    UUID currentPlayerID;
    GameTimeQueue gTimeQueue = new GameTimeQueue();

    public Game(){
        this.grid = new char[6][9];
        
    }

    public void updateTimeStamp(UUID playerID){
        Map<UUID, Timestamp> cur = gTimeQueue.getMap();
        cur.put(playerID, new Timestamp(System.currentTimeMillis()));
    }

    public boolean timeOut(GameTimeQueue gameTimeQueue){
        Map<UUID, Timestamp> cur = gTimeQueue.getMap();
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        for(Timestamp ts : cur.values()){
            if(getTimeDiff(ts, curTime) > 60000){
                return true;
            }
        }
        return false;
    }

    public static long getTimeDiff(Timestamp prev, Timestamp cur){
        long diffInMs = cur.getTime() - prev.getTime();
        System.out.println(diffInMs);
        return diffInMs;
    }


    public void chooseAndDrop(UUID playerID, int col, int row){

        if(!isValidPos(col, row)){
            System.out.println("invalid col or row number");
        }
        if(playerID.compareTo(firstPlayer.getId()) == 0){
            grid[row-1][col-1] = 'X';
            
        } else {
            grid[row-1][col-1] = 'O';
            
        }
             
    }

    public boolean checkWin(UUID playerID, int col, int row){
        char c = ' ';
        if(playerID.compareTo(firstPlayer.getId()) == 0){
            c = 'X';

        } else{
            c = 'O';
        }
        //convert to index
        col = col-1;
        row = row-1;
        int expected = 4;
        int count =0; 
        int n = grid.length;
        int m = grid[0].length;
        //check vertical direction
        for(int i = row-1, j = col; i >= 0 ;i--){
            if(grid[i][j] == c) {
                count++;
            } else {
                break;
            }
            if(count == expected) return true;
        }
        
        count = 0; 
        for(int i = row+1, j = col; i < n ;i++){
            if(grid[i][j] == c) {
                count++;
            } else {
                break;
            }
            if(count == expected) return true;
        }
        //check horizontal diration
        count = 0;
        for(int i = row, j = col+1; j < n ;j++){
            if(grid[i][j] == c) {
                count++;
            } else {
                break;
            }
            if(count == expected) return true;
        }
        count = 0;
        for(int i = row, j = col-1; j >= 0 ;j--){
            if(grid[i][j] == c) {
                count++;
            } else {
                break;
            }
            if(count == expected) return true;
        }
        count = 0;
        //check diagonal direction
        for(int i = row+1, j = col+1; i< n && j < m;j++,i++){
            if(grid[i][j] == c) {
                count++;
            } else {
                break;
            }
            if(count == expected) return true;
        } 
        count = 0;
        for(int i = row-1, j = col-1; i>=0 && j >=0 ;j--,i--){
            if(grid[i][j] == c) {
                count++;
            } else {
                break;
            }
            if(count == expected) return true;
        }
        count = 0;
        for(int i = row-1, j = col+1; i>=0 && j < m ;j++,i--){
            if(grid[i][j] == c) {
                count++;
            } else {
                break;
            }
            if(count == expected) return true;
        }
        count = 0;
        for(int i = row+1, j = col-1; i<n && j >=0 ;j--,i++){
            if(grid[i][j] == c) {
                count++;
            } else {
                break;
            }
            if(count == expected) return true;
        }

        return false;


    }
    
    public boolean isValidIndex(int x, int y, char[][] grid, char c){
        if(x < 0 || x >= grid.length) return false;
        if(y < 0 || y >= grid[0].length) return false;
        return grid[x][y] == c ? true : false;
    }
    public boolean isValidPos(int col, int row){
        if(col > 9 || col < 1) return false;
        if(row > 6 || row < 1) return false;
        if(grid[row-1][col-1] != '\0') return false;
        return true;
    }

    public static void exitApplication() {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringApplication.class);
        int exitCode = SpringApplication.exit(ctx, (ExitCodeGenerator) () -> 0);

        System.exit(exitCode);
    }


}
