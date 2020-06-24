package com.learning.skilclasses.models;

public class AssignmentBean {

    private String aid,aname,aofclass,adesp,adate,asub,apath;
    public AssignmentBean(){}

    public AssignmentBean(String aid, String aname, String aofclass, String adesp, String adate, String asub, String apath) {

        this.aid=aid;
        this.aname=aname;
        this.aofclass=aofclass;
        this.adesp=adesp;
        this.adate=adate;
        this.asub=asub;
        this.apath=apath;

    }


    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAofclass() {
        return aofclass;
    }

    public void setAofclass(String aofclass) {
        this.aofclass = aofclass;
    }

    public String getAdesp() {
        return adesp;
    }

    public void setAdesp(String adesp) {
        this.adesp = adesp;
    }

    public String getAdate() {
        return adate;
    }

    public void setAdate(String adate) {
        this.adate = adate;
    }

    public String getAsub() {
        return asub;
    }

    public void setAsub(String asub) {
        this.asub = asub;
    }

    public String getApath() {
        return apath;
    }

    public void setApath(String apath) {
        this.apath = apath;
    }
}
