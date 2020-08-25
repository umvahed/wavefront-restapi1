package com.wavefront.service;

import com.wavefront.dto.ShapeDTO;
import com.wavefront.exception.ShapeException;

import java.util.List;

public interface ShapeService {

    /**
     * get all shapes
     * @return
     */
    public List<ShapeDTO> getShapes();

    /**
     * create a new shape
     * @param shapeDTO
     * @return
     * @throws ShapeException
     */
    public ShapeDTO createShape(ShapeDTO shapeDTO) throws ShapeException;

}
