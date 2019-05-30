package by.delta.controller;

import by.delta.dto.FaceDto;
import by.delta.service.IFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/faces", produces = MediaType.APPLICATION_JSON_VALUE)
public class FacesController {

    @Autowired
    private IFaceService faceService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public FaceDto getFaceById(@PathVariable("id") final Long id) {
        return faceService.getFaceById(id);
    }
}
