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

  public <U> Result<U, E> map(Function<T, U> f) {
    return isValid
      ? Result.result(f.apply(result))
      : Result.error(error);
  }

  public <F> Result<T, F> mapError(Function<E, F> f) {
    return !isValid
      ? Result.error(f.apply(error))
      : Result.result(result);
  }

  public <U, F> Result<U, F> mapBoth(Function<T, U> f, Function<E, F> g) {
    return isValid
      ? Result.result(f.apply(result))
      : Result.error(g.apply(error));
  }

  public <U> Result<U, E> bind(Function<T, Result<U, E>> f) {
    return isValid
      ? f.apply(result)
      : Result.error(error);
  }

}
