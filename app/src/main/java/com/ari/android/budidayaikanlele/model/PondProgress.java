package com.ari.android.budidayaikanlele.model;

/**
 * Created by David on 10/10/2017.
 */

public class PondProgress {
    int id;
    int pond_id;
    int progress_id;

    public PondProgress(){
    }

    public PondProgress(int id, int pond_id, int progress_id){
        this.id = id;
        this.pond_id = pond_id;
        this.progress_id = progress_id;
    }

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

    public int getProgress_id() {
        return progress_id;
    }

    public void setProgress_id(int progress_id) {
        this.progress_id = progress_id;
    }
}
