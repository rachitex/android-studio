package com.dbsimple;

public class MyTable {

    String _roll;
    String _name;
    String _marks;

    public MyTable(){}

    public MyTable(String _roll, String _name, String _marks){
        this._roll=_roll;
        this._name=_name;
        this._marks=_marks;
    }

    public MyTable(String _name, String _marks){
        this._name=_name;
        this._marks=_marks;
    }

    //Setters
    public void setRoll(String _roll){
        this._roll=_roll;
    }

    public void setName(String _name){
        this._name=_name;
    }

    public void setMarks(String _marks){
        this._marks=_marks;
    }

    //Getters
    public String getRoll(){
        return this._roll;
    }

    public String getName(){
        return this._name;
    }

    public String getMarks(){
        return this._marks;
    }
}
