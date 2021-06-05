package com.serauds.repository;

import com.serauds.model.entity.InformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationEntityRepository extends JpaRepository<InformationEntity,Long> {
}
