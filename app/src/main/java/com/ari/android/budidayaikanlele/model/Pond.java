package com.ari.android.budidayaikanlele.model;

/**
 * Created by David on 10/10/2017.
 */

public class Pond {
    int id;
    String name;
    int seed_amount;
    String inital_date;

    public Pond(){
    }

    public Pond(String name, int seed_amount, String initial_date){
        this.name = name;
        this.seed_amount = seed_amount;
        this.inital_date = initial_date;
    }

    public Pond(int id, String name, int seed_amount, String inital_date){
        this.id = id;
        this.name = name;
        this.seed_amount = seed_amount;
        this.inital_date = inital_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeed_amount() {
        return seed_amount;
    }

    public void setSeed_amount(int seed_amount) {
        this.seed_amount = seed_amount;
    }

    public String getInital_date() {
        return inital_date;
    }

    public void setInital_date(String inital_date) {
        this.inital_date = inital_date;
    }
}
