package com.wavefront.service.impl;

import com.wavefront.Util;
import com.wavefront.dto.ShapeDTO;
import com.wavefront.entity.Shape;
import com.wavefront.exception.ShapeException;
import com.wavefront.repository.ShapeRepository;
import com.wavefront.service.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * this class is responsible for creating and fetching shape
 * The class will never entity object uses data transfer object
 * for representing shape object.
 *
 *
 */
@Service
public class ShapeServiceImpl implements ShapeService {

    @Autowired /**Auto create instance**/
    private ShapeRepository shapeRepository;

    /**
     * get all shapes
     * @return
     */
    @Override
    public List<ShapeDTO> getShapes() {
        List<ShapeDTO>list = new ArrayList<>();
        for(Shape shape: shapeRepository.findAll()) {
            list.add(toShapeDTO(shape));
        }
        return list;
    }

    /**
     * create a new shape and returns a
     * shape dto
     *
     * @param shapeDTO
     * @return
     */
    @Override
    public ShapeDTO createShape(ShapeDTO shapeDTO) throws ShapeException {
         // check shape contains type value
         if(shapeDTO.getType() == null || shapeDTO.getType().isEmpty()) throw new ShapeException("'type' is required for a shape..");
         if(!"square".equalsIgnoreCase(shapeDTO.getType())) throw new ShapeException("sorry only 'square' shape supports now..");

        // check for overlap and/or exists
        overlapShape(shapeDTO);

        Shape shape = new Shape();
        shape.setGeometryDescriptor(shapeDTO.getGeometryDescriptor());
        shape.setName(UUID.randomUUID().toString());
        shape.setType(shapeDTO.getType());

        Shape savedShape = shapeRepository.save(shape);

        return toShapeDTO(savedShape);
    }

    /**
     * converts a shape entity to shape transfer object
     * @param shape
     * @return
     */
    private ShapeDTO toShapeDTO(Shape shape) {
        ShapeDTO shapeDTO = new ShapeDTO();
        shapeDTO.setId(shape.getId());
        shapeDTO.setName(shape.getName());
        shapeDTO.setGeometryDescriptor(shape.getGeometryDescriptor());
        shapeDTO.setType(shape.getType());

        return shapeDTO;
    }

    /**
     * checks whether two squares are overlapping each other
     *
     * @param shapeDTO
     * @return
     */
    private boolean overlapShape(ShapeDTO shapeDTO) throws ShapeException {
        List<Shape>shapes = shapeRepository.findAll();
        for(Shape shape: shapes) {
            if(shape.getGeometryDescriptor().equalsIgnoreCase(shapeDTO.getGeometryDescriptor())) throw new ShapeException("this shape already exists..");
        }

        // check for overlap
        String descriptor = shapeDTO.getGeometryDescriptor();
        String[]points = descriptor.split(",");

        Point l1 = new Point(Integer.parseInt(points[0]), Integer.parseInt(points[1]));
        Point r1 = new Point(Integer.parseInt(points[4]), Integer.parseInt(points[5]));

        for(Shape shape: shapes) {

            String descriptor1 = shape.getGeometryDescriptor();
            String[]points1 = descriptor1.split(",");

            Point l2 = new Point(Integer.parseInt(points1[0]), Integer.parseInt(points1[1]));
            Point r2 = new Point(Integer.parseInt(points1[4]), Integer.parseInt(points1[5]));

            if(Util.overlap(l1,r1, l2,r2)) throw new ShapeException("squares overlap each other..");
        }

        return false;
    }

}
