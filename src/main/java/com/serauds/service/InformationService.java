package com.serauds.service;

import com.serauds.model.entity.CurrentStatus;
import com.serauds.model.entity.InformationEntity;
import com.serauds.repository.CurrentStatusRepository;
import com.serauds.repository.InformationEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class InformationService {
    @Autowired
    CurrentStatusRepository statusRepository;

    @Autowired
    InformationEntityRepository entityRepository;

    public List<InformationEntity> informationEntities() {
        return entityRepository.findAll();
    }

    public InformationEntity getInformationEntityById(Long id) {
        Optional<InformationEntity> informationEntity = entityRepository.findById(id);
        if (informationEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return informationEntity.get();
    }

    public InformationEntity postInformationEntity(InformationEntity informationEntity) {
        return entityRepository.save(informationEntity);
    }

    @Transactional
    public InformationEntity postCurrentStatus(Long id, CurrentStatus currentStatus) {
        InformationEntity informationEntity = getInformationEntityById(id);

        CurrentStatus savedStatus = statusRepository.save(currentStatus);
        List<CurrentStatus> statusList = informationEntity.getStatusList();
        statusList.add(savedStatus);
        informationEntity.setStatusList(statusList);

        return entityRepository.save(informationEntity);
    }


}
