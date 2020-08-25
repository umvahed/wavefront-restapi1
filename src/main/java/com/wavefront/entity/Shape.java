package com.wavefront.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * this is an entity class which is used to map
 * the table name in the database.
 *
 */
@Entity
public class Shape {

    /**
     * unique id
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * random generated name
     */
    @Column
    private String name;

    /**
     * store comma separated geometryDescriptor to the table
     */
    @Column
    private String geometryDescriptor;

    /**
     * store shape type (square, circle, triangle)
     */
    @Column
    private String type;
    /**PK of the table**/
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

    public String getGeometryDescriptor() {
        return geometryDescriptor;
    }

    public void setGeometryDescriptor(String geometryDescriptor) {
        this.geometryDescriptor = geometryDescriptor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
