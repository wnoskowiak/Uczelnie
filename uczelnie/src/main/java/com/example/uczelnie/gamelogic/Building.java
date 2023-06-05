package com.example.uczelnie.gamelogic;

import java.util.List;

public abstract class Building {
    private String name;
    private int currentLVL;
    private Building upgrade1;
    private Building upgrade2;
    private String description;
    private double studentEfficiencyModifier;
    private long cost;
    private List<Unlock> unlocks;
}
