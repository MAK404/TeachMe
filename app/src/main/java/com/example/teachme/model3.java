package com.example.teachme;

public class model3 {
    String taddress;
    String tem;
    String tid;
    String tland;
    String tmail;
    String tmid;
    String tname;
    String tschedule;
    String twno;
    String mno;
    String tmode;

    public model3(String taddress, String tem, String tid, String tland, String tmail, String tmid, String tname, String tschedule, String twno, String mno,String tmode) {
        this.taddress = taddress;
        this.tem = tem;
        this.tid = tid;
        this.tland = tland;
        this.tmail = tmail;
        this.tmid = tmid;
        this.tname = tname;
        this.tschedule = tschedule;
        this.twno = twno;
        this.tmode=tmode;
        this.mno=mno;
    }
    public model3()
    {

    }

    public String getTaddress() {
        return taddress;
    }

    public void setTaddress(String taddress) {
        this.taddress = taddress;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTland() {
        return tland;
    }

    public void setTland(String tland) {
        this.tland = tland;
    }

    public String getTmail() {
        return tmail;
    }

    public void setTmail(String tmail) {
        this.tmail = tmail;
    }

    public String getTmid() {
        return tmid;
    }

    public void setTmid(String tmid) {
        this.tmid = tmid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTschedule() {
        return tschedule;
    }

    public void setTschedule(String tschedule) {
        this.tschedule = tschedule;
    }

    public String getTwno() {
        return twno;
    }

    public void setTwno(String twno) {
        this.twno = twno;
    }



    public String getTmode() {
        return tmode;
    }

    public void setTmode(String tmode) {
        this.tmode = tmode;
    }
    public String getmno() {
        return mno;
    }

    public void setmno(String mno) {
        this.mno = mno;
    }


}
