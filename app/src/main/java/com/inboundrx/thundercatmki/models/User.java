package com.inboundrx.thundercatmki.models;

/**
 * Created by arlen on 7/28/17.
 */

public class User {
    private String name;
    private String id;
    private String status;
    private Integer visitCount;


    public User(){}

    public String getName() { return name; }

    public String getId() { return id; }

    public String getStatus() { return status; }

    public Integer getVisitCount() { return visitCount; }


    public User(String name, String id, String status, Integer visitCount){
        this.status = status;
        this.name = name;
        this.id = id;
        this.visitCount = visitCount;

    }
}
