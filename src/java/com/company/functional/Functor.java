package com.company.functional;

import java.util.function.Function;

public interface Functor<T> {

  public <E, F> T map(Function<E, F> f);

}
