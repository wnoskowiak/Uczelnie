package com.example.uczelnie.gamelogic;

import java.util.ArrayList;
import java.util.List;

public class Village {
    public int id;
    private String name;
    private long students;
    private double avgStudentQuality;
    private List<Building> faculties;
    private int thlvl;

    public int getThlvl() {
        return thlvl;
    }

    public String getName() {
        return "" + name;
    }

    public int getId() {
        return id;
    }

    public double getAvgStudentQuality() {
        return avgStudentQuality;
    }

    public Integer getMoney() {
        return money;
    }

    public List<Building> getFaculties() {
        return faculties;
    }

    public long getNumOfStudents() {
        return students;
    }

    public Village(String name, int id) {
        this.name = name;
        this.id = id;
        this.avgStudentQuality = 1;
        this.students = 23;
        this.faculties = new ArrayList<>();
        this.faculties.add(Building.starter());

    }

    public int buildingsBuilt() {
        return this.faculties.size();
    }
}
