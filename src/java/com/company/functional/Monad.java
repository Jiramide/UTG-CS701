package com.company.functional;

import java.util.function.Function;

public interface Monad<E> extends Functor<E> { /*

  public static <E> Monad<E> unit(E val);

  public static <E> Monad<E> join(Monad<Monad<E>> mon) {
    return mon.bind(x -> x);
  }

  default public <F> Monad<F> bind(Function<E, Monad<F>> f) {
    return Monad.join((Monad<Monad<F>>)map(f));
  }

*/ }
