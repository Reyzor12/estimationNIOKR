package org.eleron.lris.niokr.model;

import java.io.Serializable;

public abstract class Model implements Serializable{

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Model(){

    }

    public Model(Long id){
        this.id = id;
    }

}
