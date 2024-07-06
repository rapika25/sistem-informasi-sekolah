package com.sis.app.repo;

import com.sis.app.entitity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaksiRepo extends JpaRepository<Transaksi, Integer> {
}
