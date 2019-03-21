package com.company;

import java.util.function.Function;

public class Option<T> {

  public boolean hasValue;
  public T val;

  public Option() {
    this.hasValue = false;
    this.val = null;
  }

  public Option(T val) {
    this.hasValue = true;
    this.val = val;
  }

  public <U> Option<U> fmap(Function<T, U> f) {
    return hasValue ? new Option<>(f.apply(val)) : new Option<>();
  }

  public <U> Option<U> bind(Function<T, Option<U>> f) {
    return hasValue ? f.apply(val) : new Option<>();
  }

  public <U> U maybe(Function<T, U> f, U def) {
    return hasValue ? f.apply(val) : def;
  }

  public static <T> T fromOption(Option<T> opt, T def) {
    return opt.hasValue ? opt.val : def;
  }

}
