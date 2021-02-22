package com.paulorpc.aquarium.api.services.impl;

import com.paulorpc.aquarium.api.entities.SystemInfo;
import org.springframework.stereotype.Service;

@Service
public class SystemInfoService {

  public SystemInfo info() {
    return new SystemInfo();
  }
}
