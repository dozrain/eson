package com.rain.model.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-5-29 0029.
 */
public class Coordinates {
    List<Integer> xlist = new ArrayList<>();

    List<Integer> ylist = new ArrayList<>();

    public List<Integer> getXlist() {
        return xlist;
    }

    public void setXlist(List<Integer> xlist) {
        this.xlist = xlist;
    }

    public List<Integer> getYlist() {
        return ylist;
    }

    public void setYlist(List<Integer> ylist) {
        this.ylist = ylist;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "\nxlist=" + xlist + ", \nylist=" + ylist + '}';
    }
}
