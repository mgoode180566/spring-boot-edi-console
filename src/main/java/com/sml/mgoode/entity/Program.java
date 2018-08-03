package com.sml.mgoode.entity;

/**
 * Created by michaelgoode on 17/01/2018.
 */
public class Program {

    Long programId;
    String programName;
    boolean processLock;
    String creator;
    boolean enabled;
    String mainProgram;
    String subProgram;
    String status;
    boolean showAsFavourite;
    String server;

    public String getMainProgram() {
        return mainProgram;
    }

    public void setMainProgram(String mainProgram) {
        this.mainProgram = mainProgram;
    }

    public String getSubProgram() {
        return subProgram;
    }

    public void setSubProgram(String subProgram) {
        this.subProgram = subProgram;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public boolean isProcessLock() {
        return processLock;
    }

    public void setProcessLock(boolean processLock) {
        this.processLock = processLock;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isShowAsFavourite() {
        return showAsFavourite;
    }

    public void setShowAsFavourite(boolean showAsFavourite) {
        this.showAsFavourite = showAsFavourite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
