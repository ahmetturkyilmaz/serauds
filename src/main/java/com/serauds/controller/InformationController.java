package com.serauds.controller;

import com.serauds.model.entity.CurrentStatus;
import com.serauds.model.entity.InformationEntity;
import com.serauds.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info")
public class InformationController {

    @Autowired
    InformationService service;

    @GetMapping("/{id}")
    public ResponseEntity<InformationEntity> getInformationById(@PathVariable Long id) {
        InformationEntity information = service.getInformationEntityById(id);
        return ResponseEntity.ok(information);
    }

    @PostMapping()
    public ResponseEntity<InformationEntity> postInformation(@RequestBody InformationEntity informationEntity) {
        InformationEntity information = service.postInformationEntity(informationEntity);
        return ResponseEntity.ok(information);
    }

    @PostMapping("/{id}")
    public ResponseEntity<InformationEntity> postCurrentStatus(@PathVariable Long id, @RequestBody CurrentStatus status) throws Exception {
        InformationEntity information = service.postCurrentStatus(id,status);
        return ResponseEntity.ok(information);
    }
}
