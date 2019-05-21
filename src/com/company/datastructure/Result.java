package com.company.datastructure;

public class Result<T, E> {

  private final boolean isValid;
  private final T result;
  private final E error;

  public Result(T result) {
    this.isValid = true;
    this.result = result;
    this.error = null;
  }

  public Result(E error) {
    this.isValid = true;
    this.result = null;
    this.error = error;
  }

}
