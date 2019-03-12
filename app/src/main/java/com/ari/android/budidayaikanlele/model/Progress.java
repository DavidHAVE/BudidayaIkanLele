package com.ari.android.budidayaikanlele.model;

/**
 * Created by David on 10/10/2017.
 */

public class Progress {
    int id;
    String month;
    double weight1;
    double feed_weight1;
    double weight2;
    double feed_weight2;
    double weight3;
    double feed_weight3;

    public Progress(){
    }

//    public Progress(int weight1, int feed_weight1, int weight2, int feed_weight2,
//                    int weight3, int feed_weight3){
//        this.weight1 = weight1;
//        this.feed_weight1 = feed_weight1;
//        this.weight2 = weight2;
//        this.feed_weight2 = feed_weight2;
//        this.weight3 = weight3;
//        this.feed_weight3 = feed_weight3;
//    }

    public Progress(String month, double weight1, double feed_weight1, double weight2, double feed_weight2,
                    double weight3, double feed_weight3){
        this.month = month;
        this.weight1 = weight1;
        this.feed_weight1 = feed_weight1;
        this.weight2 = weight2;
        this.feed_weight2 = feed_weight2;
        this.weight3 = weight3;
        this.feed_weight3 = feed_weight3;
    }

    public Progress(int id, String month, double weight1, double feed_weight1, double weight2, double feed_weight2,
                    double weight3, double feed_weight3){
        this.id = id;
        this.month = month;
        this.weight1 = weight1;
        this.feed_weight1 = feed_weight1;
        this.weight2 = weight2;
        this.feed_weight2 = feed_weight2;
        this.weight3 = weight3;
        this.feed_weight3 = feed_weight3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getWeight1() {
        return weight1;
    }

    public void setWeight1(double weight1) {
        this.weight1 = weight1;
    }

    public double getFeed_weight1() {
        return feed_weight1;
    }

    public void setFeed_weight1(double feed_weight1) {
        this.feed_weight1 = feed_weight1;
    }

    public double getWeight2() {
        return weight2;
    }

    public void setWeight2(double weight2) {
        this.weight2 = weight2;
    }

    public double getFeed_weight2() {
        return feed_weight2;
    }

    public void setFeed_weight2(double feed_weight2) {
        this.feed_weight2 = feed_weight2;
    }

    public double getWeight3() {
        return weight3;
    }

    public void setWeight3(double weight3) {
        this.weight3 = weight3;
    }

    public double getFeed_weight3() {
        return feed_weight3;
    }

    public void setFeed_weight3(double feed_weight3) {
        this.feed_weight3 = feed_weight3;
    }
}


