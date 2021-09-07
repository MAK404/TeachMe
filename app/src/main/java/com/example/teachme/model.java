package com.example.teachme;

public class model {
    String taddress;
    String tage;
    String tcity;
    String stgender;
    String timing;
    String tmail;
    String tmobile;
    String tmode;
    String tname;
    String tpin;
    String tskills;
    String tstate;
    String tutortype;
    String tid;


    double trating;

    public model(String taddress, String tage, String tcity, String stgender, String timing, String tmail, String tmobile, String tmode, String tname, String tpin, String tskills, String tstate, String tutortype, String tid,double trating) {
        this.taddress = taddress;
        this.tage = tage;
        this.tcity = tcity;
        this.stgender = stgender;
        this.timing = timing;
        this.tmail = tmail;
        this.tmobile = tmobile;
        this.tmode = tmode;
        this.tname = tname;
        this.tpin = tpin;
        this.tskills = tskills;
        this.tstate = tstate;
        this.tutortype = tutortype;
        this.tid=tid;
        this.trating=trating;
    }
    public  model()
    {

    }


    public String getTaddress() {
        return taddress;
    }

    public void setTaddress(String taddress) {
        this.taddress = taddress;
    }

    public String getTage() {
        return tage;
    }

    public void setTage(String tage) {
        this.tage = tage;
    }

    public String getTcity() {
        return tcity;
    }

    public void setTcity(String tcity) {
        this.tcity = tcity;
    }

    public String getStgender() {
        return stgender;
    }

    public void setStgender(String stgender) {
        this.stgender = stgender;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getTmail() {
        return tmail;
    }

    public void setTmail(String tmail) {
        this.tmail = tmail;
    }

    public String getTmobile() {
        return tmobile;
    }

    public void setTmobile(String tmobile) {
        this.tmobile = tmobile;
    }

    public String getTmode() {
        return tmode;
    }

    public void setTmode(String tmode) {
        this.tmode = tmode;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTpin() {
        return tpin;
    }

    public void setTpin(String tpin) {
        this.tpin = tpin;
    }

    public String getTskills() {
        return tskills;
    }

    public void setTskills(String tskills) {
        this.tskills = tskills;
    }

    public String getTstate() {
        return tstate;
    }

    public void setTstate(String tstate) {
        this.tstate = tstate;
    }

    public String getTutortype() {
        return tutortype;
    }

    public void setTutortype(String tutortype) {
        this.tutortype = tutortype;
    }
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }



    public double getTrating() {
        return trating;
    }

    public void setTrating(double trating) {
        this.trating = trating;
    }


}
