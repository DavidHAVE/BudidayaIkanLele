package com.ari.android.budidayaikanlele.model;

/**
 * Created by David on 10/10/2017.
 */

public class Report {
    int id;
    int pond_id;
    String harvestDate;
    double totalFeed1;
    double totalFeed2;
    double totalFeed3;

    public Report(){
    }

    public Report(int pond_id, String harvestDate, double totalFeed1, double totalFeed2, double totalFeed3){
        this.pond_id = pond_id;
        this.harvestDate = harvestDate;
        this.totalFeed1 = totalFeed1;
        this.totalFeed2 = totalFeed2;
        this.totalFeed3 = totalFeed3;
    }


//    public Report(int id, int pond_id, String harvestDate, double totalFeed1, double totalFeed2, double totalFeed3){
//        this.id = id;
//        this.pond_id = pond_id;
//        this.harvestDate = harvestDate;
//        this.totalFeed1 = totalFeed1;
//        this.totalFeed2 = totalFeed2;
//        this.totalFeed3 = totalFeed3;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPond_id() {
        return pond_id;
    }

    public void setPond_id(int pond_id) {
        this.pond_id = pond_id;
    }

    public String getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(String harvestDate) {
        this.harvestDate = harvestDate;
    }

    public double getTotalFeed1() {
        return totalFeed1;
    }

    public void setTotalFeed1(double totalFeed1) {
        this.totalFeed1 = totalFeed1;
    }

    public double getTotalFeed2() {
        return totalFeed2;
    }

    public void setTotalFeed2(double totalFeed2) {
        this.totalFeed2 = totalFeed2;
    }

    public double getTotalFeed3() {
        return totalFeed3;
    }

    public void setTotalFeed3(double totalFeed3) {
        this.totalFeed3 = totalFeed3;
    }
}
