package com.serauds.repository;

import com.serauds.model.entity.CurrentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentStatusRepository extends JpaRepository<CurrentStatus, Long> {
}
