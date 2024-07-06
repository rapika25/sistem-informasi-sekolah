package com.sis.app.service;

import com.sis.app.entitity.Transaksi;
import com.sis.app.web.BaseResponse;

public interface TransaksiService {
    BaseResponse getAllTransaksi(int page, int limit, String search);
    BaseResponse getTransaksiById(String id);
    BaseResponse saveTransaksi(Transaksi transaksi);
    BaseResponse updateTransaksi(Transaksi transaksi);
    BaseResponse deleteTransaksi(String id);
}
