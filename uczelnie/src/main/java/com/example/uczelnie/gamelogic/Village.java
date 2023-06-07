package com.example.uczelnie.gamelogic;

import java.util.ArrayList;
import java.util.List;

public class Village {
    public int id;
    private String name;
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

    public List<Building> getFaculties() {
        return faculties;
    }

    public long getNumOfStudents() {
        var acc = 0;
        for (Building b : this.faculties) {
            acc += b.getStudents();
        }
        return acc;
    }

    public Village(String name, int id) {
        this.name = name;
        this.id = id;
        this.avgStudentQuality = 1;
        this.faculties = new ArrayList<>();
        this.faculties.add(Building.starter(this));

    }

    public int buildingsBuilt() {
        return this.faculties.size();
    }
}
