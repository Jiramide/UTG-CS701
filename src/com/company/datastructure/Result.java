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

  public E fromError(E defaultVal) {
      return !isValid
        ? error
        : defaultVal;
  }

  public <U> Result<U, E> map(Function<T, U> f) {
    return mapBoth(f, x -> x);
  }

  public <F> Result<T, F> mapError(Function<E, F> f) {
    return mapBoth(x -> x, f);
  }

  public <U, F> Result<U, F> mapBoth(Function<T, U> f, Function<E, F> g) {
    return isValid
      ? Result.result(f.apply(result))
      : Result.error(g.apply(error));
  }

  public <U> Result<U, E> bind(Function<T, Result<U, E>> f) {
    return bindBoth(f, Result::error);
  }

  public <F> Result<T, F> bindError(Function<E, Result<T, F>> f) {
    return bindBoth(Result::result, f);
  }

  public <U, F> Result<U, F> bindBoth(Function<T, Result<U, F>> f, Function<E, Result<U, F>> g) {
    return isValid
      ? f.apply(result)
      : g.apply(error);
  }

  @Override
  public String toString() {
    return isValid
      ? "Result(" + result.toString() + ")"
      : "Error(" + error.toString() + ")";
  }

}
