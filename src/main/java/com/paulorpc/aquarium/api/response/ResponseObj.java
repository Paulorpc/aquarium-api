package com.paulorpc.aquarium.api.response;

import java.net.URI;
import com.paulorpc.aquarium.api.util.Global;

public class ResponseObj<T> implements Response {

  private final String path;
  private T data;

  public ResponseObj(URI uri) {
    this.path = Global.getPath(uri);
  }

  public ResponseObj(URI uri, T data) {
    this.path = Global.getPath(uri);
    this.data = data;
  }

  @Override
  public String getPath() {
    return path;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

}
