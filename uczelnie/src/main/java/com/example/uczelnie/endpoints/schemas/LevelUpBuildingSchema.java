package com.example.uczelnie.endpoints.schemas;

public class LevelUpBuildingSchema {
    public final int BuildingID;
    public final int UserID;

    public LevelUpBuildingSchema(int BuildingID, int UserID) {
        this.UserID = UserID;
        this.BuildingID = BuildingID;
    }
    
}
