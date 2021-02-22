package com.paulorpc.aquarium.api.controllers;

import com.paulorpc.aquarium.api.entities.SystemInfo;
import com.paulorpc.aquarium.api.services.impl.SystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/info")
public class SystemInfoController {

  @Autowired private SystemInfoService systemInfoService;

  @GetMapping
  public ResponseEntity<SystemInfo> info() {
    return ResponseEntity.ok(systemInfoService.info());
  }
}
