package com.sis.app.repo;

import com.sis.app.entitity.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiswaRepo extends JpaRepository<Siswa, Integer> {
}
