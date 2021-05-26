package com.example.server.game;

import java.util.Random;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class Player {
    
    private String name;
    
    UUID id;
    public Player(String name){
        this.name = name;
        
        this.id = UUID.randomUUID();
    }
    public Player(UUID uuid){
        this.id = uuid;
    }


}
