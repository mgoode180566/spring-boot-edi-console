package com.sml.mgoode.entity;

/**
 * Created by michaelgoode on 18/01/2018.
 */
public class Favourite {

    String mainProgramID;
    String subProgramID;

    public Favourite(String mainProgramID, String subProgramID) {
        this.mainProgramID = mainProgramID;
        this.subProgramID = subProgramID;
    }

    public String getMainProgramID() {
        return mainProgramID;
    }

    public void setMainProgramID(String mainProgramID) {
        this.mainProgramID = mainProgramID;
    }

    public String getSubProgramID() {
        return subProgramID;
    }

    public void setSubProgramID(String subProgramID) {
        this.subProgramID = subProgramID;
    }
}
