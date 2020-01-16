package com.example.user1.eattendence;


public class DataModel {

    String present;
    String subName;
    String semText;
    int id_;

    String st_roll;

    public DataModel(String name, String sem, int id) {
        this.subName = name;
        this.semText=sem;
        this.id_ = id;
    }
    public DataModel(String roll)
    {
        this.st_roll=roll;
    }
    public DataModel(int id){
       // this.present=present;
        this.id_=id;
    }

    public String getName() {
        return subName;
    }

    public String getSem() {
        return semText;
    }

    public int getId() {
        return id_;
    }

    public String getStRoll(){return st_roll;}
}