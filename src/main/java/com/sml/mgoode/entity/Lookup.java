package com.sml.mgoode.entity;

/**
 * Created by michaelgoode on 06/02/2018.
 */
public class Lookup {

    long fileConfigId;
    int sequence;
    String command;
    String lookupDesc;

    public long getFileConfigId() {
        return fileConfigId;
    }

    public void setFileConfigId(long fileConfigId) {
        this.fileConfigId = fileConfigId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getLookupDesc() {
        return lookupDesc;
    }

    public void setLookupDesc(String lookupDesc) {
        this.lookupDesc = lookupDesc;
    }
}
