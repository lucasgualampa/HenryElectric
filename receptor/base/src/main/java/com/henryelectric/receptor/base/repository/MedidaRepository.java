package com.henryelectric.receptor.base.repository;

import com.henryelectric.receptor.base.model.MedidaR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedidaRepository extends JpaRepository<MedidaR, Integer> {

    @Query(nativeQuery = true, value = "select * from medidar where id_medidor = :id and fecha between :from and :to")
    List<MedidaR> findByDateBetween(Integer id, String from, String to);
}
