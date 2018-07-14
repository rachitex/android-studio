package com.mvcmultiple;

public class MyTable {
    int roll,marks;
    String name;

    //Constructors

    public MyTable(){}

    public MyTable(String name){
        this.name=name;
    }

    public MyTable(String name, int marks){
        this.name=name;
        this.marks=marks;
    }

    public MyTable(int roll, String name, int marks){
        this.roll=roll;
        this.name=name;
        this.marks=marks;
    }

    //Setter

    public void setRoll(int roll){
        this.roll=roll;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setMarks(int marks){
        this.marks=marks;
    }

    //Getter

    public int getRoll(){
        return this.roll;
    }

    public String getName(){
        return this.name;
    }

    public int getMarks(){
        return this.marks;
    }
}
