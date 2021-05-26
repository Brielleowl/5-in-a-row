package com.example.server.game;

import java.sql.Timestamp;
import java.util.*;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GameTimeQueue {
    Map<UUID, Timestamp> map;

    GameTimeQueue (){
        map = new HashMap<>();

    }
    


}
