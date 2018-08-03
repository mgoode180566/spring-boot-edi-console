package com.sml.mgoode.entity;

/**
 * Created by michaelgoode on 18/01/2018.
 */
public class Favourite {

    String name;
    String mainProgramID;
    String subProgramID;
    String server;
    long configID;
    long lookupID;

    public Favourite(String name, String mainProgramID, String subProgramID, String server, long configID, long lookupID) {
        this.name = name;
        this.mainProgramID = mainProgramID;
        this.subProgramID = subProgramID;
        this.server = server;
        this.configID = configID;
        this.lookupID = lookupID;
    }

    public Favourite(String name, String mainProgramID, String subProgramID, long configID, long lookupID) {
        this.name = name;
        this.mainProgramID = mainProgramID;
        this.subProgramID = subProgramID;
        this.server = "";
        this.configID = configID;
        this.lookupID = lookupID;
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

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public long getConfigID() {
        return configID;
    }

    public void setConfigID(long configID) {
        this.configID = configID;
    }

    public long getLookupID() {
        return lookupID;
    }

    public void setLookupID(long lookupID) {
        this.lookupID = lookupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
