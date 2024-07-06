package com.sis.app.impl;

import com.sis.app.entitity.TagihanSPP;
import com.sis.app.repo.TagihanSPPRepo;
import com.sis.app.service.TagihanSPPService;
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
public class TagihanSPPServiceImpl implements TagihanSPPService {

    @Autowired
    private TagihanSPPRepo tagihanSPPRepo;

    @Override
    public BaseResponse getAllTagihanSPP(int page, int limit, String search) {
        List<TagihanSPP> tagihanSPP = new ArrayList<>();
        HashMap<String, Object> addEntity = new HashMap<>();
        if (page < 0 || limit < 0) {
            tagihanSPP = tagihanSPPRepo.findAll();
        } else {
            Pageable pageable = Pageable.ofSize(limit).withPage(page);
            Page<TagihanSPP> tagihanSPPPage = tagihanSPPRepo.findAll(pageable);
            tagihanSPP = tagihanSPPPage.toList();

            addEntity.put("totalPage", tagihanSPPPage.getTotalPages());
            addEntity.put("totalData", tagihanSPPPage.getTotalElements());
            addEntity.put("numberOfData", tagihanSPPPage.getNumberOfElements());
            addEntity.put("number", tagihanSPPPage.getNumber());
        }

        return new BaseResponse(true, ResponseMessageConst.GET_SUCCESS.toString(), tagihanSPP, addEntity);
    }

    @Override
    public BaseResponse getTagihanSPPById(String id) {
        return new BaseResponse(true, ResponseMessageConst.GET_SUCCESS.toString(), tagihanSPPRepo.findById(Integer.valueOf(id)).orElse(null));
    }

    @Override
    public BaseResponse saveTagihanSPP(TagihanSPP tagihanSPP) {
        return new BaseResponse(true, ResponseMessageConst.ADD_SUCCESS.toString(), tagihanSPPRepo.save(tagihanSPP));
    }

    @Override
    public BaseResponse updateTagihanSPP(TagihanSPP tagihanSPP) {
        return new BaseResponse(true, ResponseMessageConst.UPDATE_SUCCESS.toString(), tagihanSPPRepo.save(tagihanSPP));
    }

    @Override
    public BaseResponse deleteTagihanSPP(String id) {
        tagihanSPPRepo.deleteById(Integer.valueOf(id));
        return new BaseResponse(true, ResponseMessageConst.DELETE_SUCCESS.toString(), null);
    }
}
