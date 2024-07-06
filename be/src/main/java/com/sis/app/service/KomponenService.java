package com.sis.app.service;

import com.sis.app.entitity.Komponen;
import com.sis.app.web.BaseResponse;

public interface KomponenService {
    BaseResponse getAllKomponen(int page, int limit, String search);
    BaseResponse getKomponenById(String id);
    BaseResponse saveKomponen(Komponen komponen);
    BaseResponse updateKomponen(Komponen komponen);
    BaseResponse deleteKomponen(String id);
}
