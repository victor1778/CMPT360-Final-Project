package com.mycompany.cmpt360_final_project;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vrfvega
 */
public class Department {
    private String name;
    private String location;
    private int budget;
    
    public Department() {
        this.name = "N/A";
        this.location = "N/A";
        this.budget = 0;
    }
    
    public Department(String n, String l, int i) {
        this.name = n;
        this.location = l;
        this.budget = i;
    }
    
    public void setName(String s) {
        this.name = s;
    }
    
    public void setLocation(String s) {
        this.location = s;
    }
    
    public void setBudget(int i) {
        this.budget = i;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public int getBudget() {
        return this.budget;
    }
}
