package com.example.teachme;

public class model2 {

    String stage;
    String stname;
    String stcity;
    String stclass;
    String stmail;
    String stmobile;
    String stmode;
    String stpin;
    String ststate;

    String sid;

    public model2(String stage, String stname, String stcity, String stclass, String stmail, String stmobile, String stmode, String stpin, String ststate,String sid) {
        this.stage = stage;
        this.stname = stname;
        this.stcity = stcity;
        this.stclass = stclass;
        this.stmail = stmail;
        this.stmobile = stmobile;
        this.stmode = stmode;
        this.stpin = stpin;
        this.ststate = ststate;
        this.sid=sid;
    }
    public model2(){}


    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStname() {
        return stname;
    }

    public void setStname(String stname) {
        this.stname = stname;
    }

    public String getStcity() {
        return stcity;
    }

    public void setStcity(String stcity) {
        this.stcity = stcity;
    }

    public String getStclass() {
        return stclass;
    }

    public void setStclass(String stclass) {
        this.stclass = stclass;
    }

    public String getStmail() {
        return stmail;
    }

    public void setStmail(String stmail) {
        this.stmail = stmail;
    }

    public String getStmobile() {
        return stmobile;
    }

    public void setStmobile(String stmobile) {
        this.stmobile = stmobile;
    }

    public String getStmode() {
        return stmode;
    }

    public void setStmode(String stmode) {
        this.stmode = stmode;
    }

    public String getStpin() {
        return stpin;
    }

    public void setStpin(String stpin) {
        this.stpin = stpin;
    }

    public String getStstate() {
        return ststate;
    }

    public void setStstate(String ststate) {
        this.ststate = ststate;
    }


    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }


}
