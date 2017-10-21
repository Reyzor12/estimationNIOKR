package org.eleron.lris.niokr.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class Model implements Serializable{

    /*
    * Model Fields
    * */

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private long id;

    /*
    * Getters Setters Methods
    * */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /*
    * Constructors
    * */

    public Model(){}

    public Model(Long id){
        this.id = id;
    }
}
