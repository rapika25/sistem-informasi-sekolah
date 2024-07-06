package com.sis.app.impl;

import com.sis.app.entitity.Komponen;
import com.sis.app.repo.KomponenRepo;
import com.sis.app.service.KomponenService;
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
public class KomponenServiceImpl implements KomponenService {

    @Autowired
    private KomponenRepo komponenRepo;

    @Override
    public BaseResponse getAllKomponen(int page, int limit, String search) {
        List<Komponen> komponen = new ArrayList<>();
        HashMap<String, Object> addEntity = new HashMap<>();
        if (page < 0 || limit < 0) {
            komponen = komponenRepo.findAll();
        } else {
            Pageable pageable = Pageable.ofSize(limit).withPage(page);
            Page<Komponen> komponenPage = komponenRepo.findAll(pageable);
            komponen = komponenPage.toList();

            addEntity.put("totalPage", komponenPage.getTotalPages());
            addEntity.put("totalData", komponenPage.getTotalElements());
            addEntity.put("numberOfData", komponenPage.getNumberOfElements());
            addEntity.put("number", komponenPage.getNumber());
        }

        return new BaseResponse(true, ResponseMessageConst.GET_SUCCESS.toString(), komponen, addEntity);
    }

    @Override
    public BaseResponse getKomponenById(String id) {
        return new BaseResponse(true, ResponseMessageConst.GET_SUCCESS.toString(), komponenRepo.findById(Integer.valueOf(id)).orElse(null));
    }

    @Override
    public BaseResponse saveKomponen(Komponen komponen) {
        return new BaseResponse(true, ResponseMessageConst.ADD_SUCCESS.toString(), komponenRepo.save(komponen));
    }

    @Override
    public BaseResponse updateKomponen(Komponen komponen) {
        return new BaseResponse(true, ResponseMessageConst.UPDATE_SUCCESS.toString(), komponenRepo.save(komponen));
    }

    @Override
    public BaseResponse deleteKomponen(String id) {
        komponenRepo.deleteById(Integer.valueOf(id));
        return new BaseResponse(true, ResponseMessageConst.DELETE_SUCCESS.toString(), null);
    }
}
