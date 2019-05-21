package com.company.datastructure;

import java.util.function.Function;

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

  public boolean isResult() {
    return isValid;
  }

  public boolean isError() {
    return !isValid;
  }

  public T getResult() {
    return result;
  }

  public E getError() {
    return error;
  }

  public T fromResult(T defaultVal) {
    return isValid
      ? result
      : defaultVal;
  }

}
