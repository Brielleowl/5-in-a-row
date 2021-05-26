package com.example.player;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    UUID uuid;
    String name;
    boolean isFirstPlayer;
    public Player(String name){
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

}
