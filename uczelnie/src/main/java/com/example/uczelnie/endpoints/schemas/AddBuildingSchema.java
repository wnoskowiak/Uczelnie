package com.example.uczelnie.endpoints.schemas;

public class AddBuildingSchema {

    public final int BuildingType;
    public final int UserID;

    public AddBuildingSchema(int BuildingType, int UserID) {
        this.UserID = UserID;
        this.BuildingType = BuildingType;
    }
    
}
