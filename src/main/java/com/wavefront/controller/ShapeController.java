package com.wavefront.controller;

import com.wavefront.dto.ShapeDTO;
import com.wavefront.exception.ShapeException;
import com.wavefront.service.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * this class has two endpoints. They are
 *  GET
 *  POST
 *  respectively
 *
 */
@RestController
public class ShapeController {

    @Autowired
    private ShapeService shapeService;

    /**
     * get all shapes
     * @return
     */
    @GetMapping("/api/shapes")
    public ResponseEntity<List<ShapeDTO>> getAllShapes() {
        return ResponseEntity.ok(shapeService.getShapes());
    }


    /**
     * creates a new shape
     * @param type
     * @param descriptor
     * @return
     * @throws ShapeException
     */
    @PostMapping(value = "/api/shapes")
    public ResponseEntity<ShapeDTO> createNewShape(@RequestParam("type") String type, @RequestParam("descriptor") String descriptor) throws ShapeException {

        ShapeDTO shapeDTO = new ShapeDTO();
        shapeDTO.setGeometryDescriptor(descriptor);
        shapeDTO.setType(type);

        ShapeDTO newShapeDTO = shapeService.createShape(shapeDTO);

        return ResponseEntity.ok(newShapeDTO);
    }

}
