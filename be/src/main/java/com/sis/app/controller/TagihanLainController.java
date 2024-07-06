package com.sis.app.controller;

import com.sis.app.entitity.TagihanLain;
import com.sis.app.impl.TagihanLainServiceImpl;
import com.sis.app.web.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/tagihan-lain")
public class TagihanLainController {

    @Autowired
    private TagihanLainServiceImpl tagihanLainServiceImpl;

    @GetMapping
    public BaseResponse getAllTagihanLain(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "limit", required = false, defaultValue = "10") int limit, @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        return tagihanLainServiceImpl.getAllTagihanLain(page, limit, search);
    }

    @GetMapping("/{id}")
    public BaseResponse getTagihanLainById(@PathVariable("id") String id) {
        return tagihanLainServiceImpl.getTagihanLainById(id);
    }

    @PostMapping
    public BaseResponse saveTagihanLain(@RequestBody TagihanLain tagihanLain) {
        return tagihanLainServiceImpl.saveTagihanLain(tagihanLain);
    }

    @PutMapping
    public BaseResponse updateTagihanLain(@RequestBody TagihanLain tagihanLain) {
        return tagihanLainServiceImpl.updateTagihanLain(tagihanLain);
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteTagihanLain(@PathVariable("id") String id) {
        return tagihanLainServiceImpl.deleteTagihanLain(id);
    }
}
