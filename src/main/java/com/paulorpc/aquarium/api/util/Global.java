package com.paulorpc.aquarium.api.util;

public class Global {

  public String nullToEmpty(Object obj) {
    return (obj == null) ? "" : obj.toString();
  }

}
