package com.henry.electric.base.repository;

import com.henry.electric.base.model.MedidorE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedidorRepository extends JpaRepository<MedidorE, Integer> {
}
