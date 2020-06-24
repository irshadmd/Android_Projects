package com.learning.skilclasses.models;

public class Video {
  /*  private String title;
    private String url,name,vofclass,vsub,date,vid;

    public String getTitle() {
        return title;
    }

    public Video(String title, String url,String name,String vid,String date,String vofclass,String vsub) {
        this.title = title;
        this.url = url;
        this.name=name;
        this.date=date;
        this.vid=vid;
        this.vsub=vsub;
        this.vofclass=vofclass;
    }
    public String getVid(String vid){
        return vid;
    }
    public void setVid(String vid){this.vid=vid;}
    public String getName(String name){return name;}
    public void setName(String name){this.name=name;}
    public String getVofclass(String vofclass){return vofclass;}
    public void setVofclass(String vofclass){this.vofclass=vofclass;}
    public String getDate(String date){return date;}
    public void setDate(String date){this.date=date;}
    public String getVsub(String vsub){return vsub;}
    public void setVsub(String vsub){
        this.vsub=vsub;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Video(String vid) {

    }*/
  private String vid,vname,vofclass,vdesp,vpath,vdate,vsub;
    public Video(){}

    public Video(String vid, String vname, String vofclass, String vdesp, String vdate, String vsub, String vpath) {

        this.vid=vid;
        this.vname=vname;
        this.vofclass=vofclass;
        this.vdesp=vdesp;
        this.vdate=vdate;
        this.vsub=vsub;
        this.vpath=vpath;

    }


    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vnane) {
        this.vname = vnane;
    }

    public String getVofclass() {
        return vofclass;
    }

    public void setVofclass(String vofclass) {
        this.vofclass = vofclass;
    }

    public String getVdesp() {
        return vdesp;
    }

    public void setVdesp(String vdesp) {
        this.vdesp = vdesp;
    }

    public String getVpath() {
        return vpath;
    }

    public void setVpath(String vpath) {
        this.vpath = vpath;
    }

    public String getVdate() {
        return vdate;
    }

    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    public String getVsub() {
        return vsub;
    }

    public void setVsub(String vsub) {
        this.vsub = vsub;
    }
}
