package com.example.ex9.controller;

import com.example.ex9.model.responseapi.MsgSuccess;
import com.example.ex9.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class BaseController<T, P> {

    @Autowired
    private BaseService<T, P> baseService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<MsgSuccess> getById(@PathVariable("id") P id) {
        T t = baseService.findOne(id);
        return new ResponseEntity<>(new MsgSuccess(t), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<MsgSuccess> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "id") String sortBy) {
        Sort.Direction direction = sortDirection.
                equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page-1, limit, sort);

        Page<T> tPage = baseService.findAll(pageable);
        List<T> tList = tPage.getContent();
        return new ResponseEntity<>(new MsgSuccess(page, tPage.getTotalElements(), limit, tList), HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<MsgSuccess> getWithSearch(
            @RequestParam(defaultValue = "##") String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "id") String sortBy) {
        Sort.Direction direction = sortDirection.
                equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page-1, limit, sort);
        Page<T> tPage = baseService.findByNameContaining(name, pageable);
        List<T> tList = tPage.getContent();
        return new ResponseEntity<>(new MsgSuccess(page, tPage.getTotalElements(), limit, tList), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MsgSuccess> create(@Valid @RequestBody T t) {
        T tNew = baseService.create(t);
        return new ResponseEntity<>(new MsgSuccess(tNew), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<MsgSuccess> update(
            @PathVariable("id") P id, @Valid @RequestBody T t){
        T tNew = baseService.updateById(id, t);
        return new ResponseEntity<>(new MsgSuccess(tNew), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") P id) {
        boolean success = baseService.deleteById(id);
        if (success) return ResponseEntity.ok().body("Entity with ID : " + id + " deleted with success!");
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
