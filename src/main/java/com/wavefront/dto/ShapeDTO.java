package com.wavefront.dto;

/**
 * this class is used as data transfer between entity layer
 * and controller layer
 */
public class ShapeDTO {

    private int id;

    private String name;

    private String geometryDescriptor;

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

    private String type;

}
