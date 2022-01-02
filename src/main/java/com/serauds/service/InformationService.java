package com.serauds.service;

import com.serauds.model.entity.CurrentStatus;
import com.serauds.model.entity.InformationEntity;
import com.serauds.repository.CurrentStatusRepository;
import com.serauds.repository.InformationEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
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
        if (!informationEntity.isPresent()) {
            throw new EntityNotFoundException();
        }
        return informationEntity.get();
    }

    public InformationEntity postInformationEntity(InformationEntity informationEntity) {
        return entityRepository.save(informationEntity);
    }

    @Transactional
    public InformationEntity postCurrentStatus(Long id, CurrentStatus currentStatus) throws Exception {
        InformationEntity informationEntity = getInformationEntityById(id);
        boolean flag = true;
        if (currentStatus.getHumidity() != null && currentStatus.getTemperature() != null) {
            if (!(currentStatus.getHumidity() < 60 && currentStatus.getHumidity() > 50)) {
                flag = false;
            }

            if (!(currentStatus.getTemperature() > 35 && currentStatus.getTemperature() < 45)) {
                flag = false;
            }
        } else {
            throw new Exception("Humidity or temperature info is not valid");
        }
        if (!flag) {
            currentStatus.setStatus("critical");
        } else {
            currentStatus.setStatus("normal");
        }

        currentStatus.setDate(new Date());
        CurrentStatus savedStatus = statusRepository.save(currentStatus);

        List<CurrentStatus> statusList = informationEntity.getStatusList();

        statusList.add(savedStatus);
        informationEntity.setStatusList(statusList);

        return entityRepository.save(informationEntity);
    }


}
