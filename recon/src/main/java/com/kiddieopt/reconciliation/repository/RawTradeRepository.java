package com.kiddieopt.reconciliation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiddieopt.reconciliation.entity.RawTrade;

@Repository
public interface RawTradeRepository extends JpaRepository<RawTrade, Long> {

    List<RawTrade> findByProcessedFlagFalse();

}
