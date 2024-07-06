package com.sis.app.impl;

import com.sis.app.entitity.TahunAjaran;
import com.sis.app.repo.TahunAjaranRepo;
import com.sis.app.service.TahunAjaranService;
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
public class TahunAjaranServiceImpl implements TahunAjaranService {

    @Autowired
    private TahunAjaranRepo tahunAjaranRepo;

    @Override
    public BaseResponse getAllTahunAjaran(int page, int limit, String search) {
        List<TahunAjaran> tahunAjaran = new ArrayList<>();
        HashMap<String, Object> addEntity = new HashMap<>();
        if (page < 0 && limit < 0) {
            tahunAjaran = tahunAjaranRepo.findAll();
        } else {
            Pageable pageable = Pageable.ofSize(limit).withPage(page);
            Page<TahunAjaran> tahunAjaranPage = tahunAjaranRepo.findAll(pageable);
            tahunAjaran = tahunAjaranPage.toList();

            addEntity.put("totalPage", tahunAjaranPage.getTotalPages());
            addEntity.put("totalData", tahunAjaranPage.getTotalElements());
            addEntity.put("numberOfData", tahunAjaranPage.getNumberOfElements());
            addEntity.put("number", tahunAjaranPage.getNumber());
        }

        return new BaseResponse(true, ResponseMessageConst.GET_SUCCESS.toString(), tahunAjaran, addEntity);
    }

    @Override
    public BaseResponse getTahunAjaranById(String id) {
        return new BaseResponse(true, ResponseMessageConst.GET_SUCCESS.toString(), tahunAjaranRepo.findById(Integer.valueOf(id)).orElse(null));
    }

    @Override
    public BaseResponse saveTahunAjaran(TahunAjaran tahunAjaran) {
        return new BaseResponse(true, ResponseMessageConst.ADD_SUCCESS.toString(), tahunAjaranRepo.save(tahunAjaran));
    }

    @Override
    public BaseResponse updateTahunAjaran(TahunAjaran tahunAjaran) {
        return new BaseResponse(true, ResponseMessageConst.UPDATE_SUCCESS.toString(), tahunAjaranRepo.save(tahunAjaran));
    }

    @Override
    public BaseResponse deleteTahunAjaran(String id) {
        tahunAjaranRepo.deleteById(Integer.valueOf(id));
        return new BaseResponse(true, ResponseMessageConst.DELETE_SUCCESS.toString(), null);
    }
}
