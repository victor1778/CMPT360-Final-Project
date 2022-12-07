/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cmpt360_final_project;
/**
 *
 * @author vrfvega
 */
public class Instructor implements Comparable<Instructor> {
    private int id;
    private String name;
    private String affiliated_department;
    
    public Instructor() {
        id = 0;
        name = "N/A";
        affiliated_department = "N/A";
    }
    
    public Instructor(int i, String n, String ad) {
        this.id = i;
        this.name = n;
        this.affiliated_department = ad;
    }
    
    public void setID(int i) {
        this.id = i;
    }
    
    public void setName(String s) {
        this.name = s;
    }
    
    public void setAffiliated_Department(String s) {
        this.affiliated_department = s;
    }
    
    public int getID() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getAffiliated_Department() {
        return this.affiliated_department;
    }
    
    @Override public int compareTo(Instructor comparestu) {
        int compareid = ((Instructor)comparestu).getID();
        return this.id - compareid;
    }
    
    @Override
    public String toString() {
        return "Name: " + this.name + "\nDepartment: " + this.affiliated_department;
    }
}
