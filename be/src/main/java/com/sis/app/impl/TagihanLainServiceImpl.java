package com.sis.app.impl;

import com.sis.app.entitity.TagihanLain;
import com.sis.app.repo.TagihanLainRepo;
import com.sis.app.service.TagihanLainService;
import com.sis.app.web.BaseResponse;
import com.sis.constanta.ResponseMessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TagihanLainServiceImpl implements TagihanLainService {

    @Autowired
    private TagihanLainRepo tagihanLainRepo;

    @Override
    public BaseResponse getAllTagihanLain(int page, int limit, String search) {
        List<TagihanLain> tagihanLain = new ArrayList<>();
        HashMap<String, Object> addEntity = new HashMap<>();
        if (page < 0 || limit < 0) {
            tagihanLain = tagihanLainRepo.findAll();
        } else {
            Pageable pageable = Pageable.ofSize(limit).withPage(page);
            Page<TagihanLain> tagihanLainPage = tagihanLainRepo.findAll(pageable);
            tagihanLain = tagihanLainPage.toList();

            addEntity.put("totalPage", tagihanLainPage.getTotalPages());
            addEntity.put("totalData", tagihanLainPage.getTotalElements());
            addEntity.put("numberOfData", tagihanLainPage.getNumberOfElements());
            addEntity.put("number", tagihanLainPage.getNumber());
        }

        return new BaseResponse(true, ResponseMessageConst.GET_SUCCESS.toString(), tagihanLain, addEntity);
    }

    @Override
    public BaseResponse getTagihanLainById(String id) {
        return new BaseResponse(true, ResponseMessageConst.GET_SUCCESS.toString(), tagihanLainRepo.findById(Integer.valueOf(id)).orElse(null));
    }

    @Override
    public BaseResponse saveTagihanLain(TagihanLain tagihanLain) {
        return new BaseResponse(true, ResponseMessageConst.ADD_SUCCESS.toString(), tagihanLainRepo.save(tagihanLain));
    }

    @Override
    public BaseResponse updateTagihanLain(TagihanLain tagihanLain) {
        return new BaseResponse(true, ResponseMessageConst.UPDATE_SUCCESS.toString(), tagihanLainRepo.save(tagihanLain));
    }

    @Override
    public BaseResponse deleteTagihanLain(String id) {
        tagihanLainRepo.deleteById(Integer.valueOf(id));
        return new BaseResponse(true, ResponseMessageConst.DELETE_SUCCESS.toString(), null);
    }
}
