package com.example.uczelnie.endpoints.schemas;

public class EditBuildingDataSchema {
    public final int UserID;
    public final int BuildingID;
    public final String NewName;

    public EditBuildingDataSchema(int UserID, int BuildingID, String NewName) {
        this.UserID = UserID;
        this.BuildingID = BuildingID;
        this.NewName = NewName;

    }
    
}
