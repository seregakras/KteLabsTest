package com.example.ktelabstest.contollers;

import com.example.ktelabstest.dto.GenericDTO;
import com.example.ktelabstest.model.GenericModel;
import com.example.ktelabstest.services.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class GenericController<T extends GenericModel, N extends GenericDTO> {

    protected final GenericService<T, N> genericService;

    protected GenericController(GenericService<T, N> genericService) {
        this.genericService = genericService;
    }


    @RequestMapping(value = "/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<N>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(genericService.listAll());
    }

    @RequestMapping(value = "/getOneById",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> getOneById(@RequestParam(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(genericService.getOne(id));
    }

    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> add(@RequestBody N newDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genericService.create(newDTO));
    }

    @RequestMapping(value = "/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> update(@RequestBody N updateDTO,
                                    @RequestParam(value = "id") Long id) {
        updateDTO.setId(id);
        genericService.create(updateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateDTO);
    }

    @RequestMapping(value = "/delete",
            method = RequestMethod.DELETE)
    public void delete(@RequestParam(value = "id") Long id) {
        genericService.delete(id);
    }
}

