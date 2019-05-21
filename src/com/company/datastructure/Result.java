package com.company.datastructure;

import java.util.function.Function;

public class Result<T, E> {

  private final boolean isValid;
  private final T result;
  private final E error;

  private Result(T result, E error) {
    this.isValid = result != null;
    this.result = result;
    this.error = error;
  }

  public static <T, E> Result<T, E> result(T result) {
    return new Result<>(result, null);
  }

  public static <T, E> Result<T, E> error(E error) {
    return new Result<>(null, error);
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
