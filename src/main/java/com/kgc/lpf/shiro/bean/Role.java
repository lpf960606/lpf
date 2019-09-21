package com.kgc.lpf.shiro.bean;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {

    private int rid;

    private String rname;

    private List<Perssion> perssion;

    public List<Perssion> getPerssion() {

        return perssion;
    }

    public void setPerssion(List<Perssion> perssion) {
        this.perssion = perssion;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
