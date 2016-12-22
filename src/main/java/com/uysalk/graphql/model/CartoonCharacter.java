package com.uysalk.graphql.model;

public  class CartoonCharacter {
    private String name;
    private boolean mainCharacter;


    public CartoonCharacter(){

    }

    public CartoonCharacter(String name, boolean mainCharacter) {
        this.name = name;
        this.mainCharacter = mainCharacter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMainCharacter() {
        return mainCharacter;
    }

    public void setMainCharacter(boolean mainCharacter) {
        this.mainCharacter = mainCharacter;
    }


}