package com.henry.electric.base.repository;

import com.henry.electric.base.model.MedidaE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedidaRepository extends JpaRepository<MedidaE, Integer> {
}
