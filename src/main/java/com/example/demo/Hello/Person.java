package com.example.demo.Hello;

import javafx.scene.control.Button;

public class Person {
    private int id;
    private String name;
    private Button button;



    public Person(int id, String name){
        this.id = id;
        this.name = name;
        this.button = new Button("Send Mail");
    }

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return name;
    }

    public void setButton(Button button){
        this.button = button;
    }

    public Button getButton(){
        return button;
    }
}
