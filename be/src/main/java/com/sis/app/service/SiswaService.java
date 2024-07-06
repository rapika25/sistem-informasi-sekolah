package com.sis.app.service;

import com.sis.app.entitity.Siswa;
import com.sis.app.web.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface SiswaService {
    BaseResponse getAllSiswa(int page, int limit, String search);
    BaseResponse getSiswaById(String id);
    BaseResponse saveSiswa(Siswa siswa, MultipartFile file);
    BaseResponse updateSiswa(Siswa siswa, MultipartFile file);
    BaseResponse deleteSiswa(String id);
}
