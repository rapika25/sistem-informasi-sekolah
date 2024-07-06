package com.sis.app.controller;

import com.sis.app.entitity.Komponen;
import com.sis.app.impl.KomponenServiceImpl;
import com.sis.app.web.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/komponen")
public class KomponenController {

    @Autowired
    private KomponenServiceImpl komponenServiceImpl;

    @GetMapping
    public BaseResponse getAllKomponen(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "limit", required = false, defaultValue = "10") int limit, @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        return komponenServiceImpl.getAllKomponen(page, limit, search);
    }

    @GetMapping("/{id}")
    public BaseResponse getKomponenById(@PathVariable("id") String id) {
        return komponenServiceImpl.getKomponenById(id);
    }

    @PostMapping
    public BaseResponse saveKomponen(@RequestBody Komponen komponen) {
        return komponenServiceImpl.saveKomponen(komponen);
    }

    @PutMapping
    public BaseResponse updateKomponen(@RequestBody Komponen komponen) {
        return komponenServiceImpl.updateKomponen(komponen);
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteKomponen(@PathVariable("id") String id) {
        return komponenServiceImpl.deleteKomponen(id);
    }
}
