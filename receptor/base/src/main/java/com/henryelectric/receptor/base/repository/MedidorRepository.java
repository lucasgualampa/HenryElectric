package com.henryelectric.receptor.base.repository;

import com.henryelectric.receptor.base.model.MedidorR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedidorRepository extends JpaRepository<MedidorR, Integer> {
}
