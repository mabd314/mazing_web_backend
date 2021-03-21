package com.mazing.logic.item;

public class NoKey {
    private NoKey(){}
    public static Key getInstance(){
        return new Key(0);
    }
}
