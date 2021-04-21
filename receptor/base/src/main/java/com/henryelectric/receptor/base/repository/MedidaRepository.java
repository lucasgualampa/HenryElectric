package com.henryelectric.receptor.base.repository;

import com.henryelectric.receptor.base.model.MedidaR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedidaRepository extends JpaRepository<MedidaR, Integer> {

}
