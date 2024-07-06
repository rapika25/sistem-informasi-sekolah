package com.sis.app.service;

import com.sis.app.entitity.TahunAjaran;
import com.sis.app.web.BaseResponse;

public interface TahunAjaranService {
    BaseResponse getAllTahunAjaran(int page, int limit, String search);
    BaseResponse getTahunAjaranById(String id);
    BaseResponse saveTahunAjaran(TahunAjaran tahunAjaran);
    BaseResponse updateTahunAjaran(TahunAjaran tahunAjaran);
    BaseResponse deleteTahunAjaran(String id);
}

