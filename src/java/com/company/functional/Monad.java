package com.company.functional;

import java.util.function.Function;

public interface Monad<T> extends Functor<T> {

  public <E> T unit(E val);
  public T join();
  public <E> T bind(Function<E, T> f);

}
