package com.paulorpc.aquarium.api.util;

import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Global {
 
  public static String nullToEmpty(String obj) {
    return (obj == null) ? "" : obj.trim();
  }

  public static String responsePath(URI uri) {
    return uri.normalize().getPath().toString();    
  }
  
  public static URI getUri() {
    return ServletUriComponentsBuilder.fromCurrentRequest().build().toUri(); 
  }

}
