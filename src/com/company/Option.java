package com.company;

import java.util.function.Function;

/**
 * @author Jade Piramide <jadepiramidepogi@gmail.com>
 * @param <T> The type held inside the Option.
 */
public class Option<T> {

  private boolean hasValue;
  private T val;

  /**
   * Constructs an empty Option.
   */
  public Option() {
    this.hasValue = false;
    this.val = null;
  }

  /**
   * Constructs a non-empty Option.
   * @param val The value stored inside the Option.
   */
  public Option(T val) {
    this.hasValue = true;
    this.val = val;
  }

  /**
   * Checks if an Option contains a value or not.
   * @return A boolean indicating whether an Option contains a value.
   */
  public boolean containsValue() {
    return hasValue;
  }

  /**
   * Returns the value of the Option.
   *
   * <p>
   * Returns the value of the Option regardless of whether the Option
   * contains a value or not. Ideally, this method should never be used and
   * Option.fromOption should be preferred as it allows for default values in the
   * scenario where the value <i> doesn't </i> exist. Use with caution.
   * </p>
   *
   * @return The value of the Option.
   */
  public T getVal() {
    return val;
  }

  /**
   * Transforms the value inside an Option<T>, if it exists.
   *
   * <p>
   * Allows for the transformation of the value inside an Option<T> o
   * if o contains a value (ie o.containsValue() == true) and rewraps that
   * value into another Option. Otherwise, it returns an empty Option.
   *
   * This function does not mutate the original Option object.
   * </p>
   *
   * @param <U> The new type which the Option will hold after transforming the value.
   * @param f The function responsible for transforming the value, if it exists.
   * @return A new Option which may contain the transformed value or be empty.
   */
  public <U> Option<U> fmap(Function<T, U> f) {
    return hasValue ? new Option<>(f.apply(val)) : new Option<>();
  }

  /**
   * Allows to sequence multiple Option actions.
   *
   * <p>
   * Allows to transform not only the value inside the Option, but the structure itself.
   * This ability to transform the structure allows the ability to sequence multiple actions
   * which may result in an Option. If one of these actions were to fail and return an empty Option,
   * the rest of the sequenced actions cannot be performed (due to the lack of value) and returns another
   * empty Option. This removes lots of the boilerplate code dealing with multiple Option values.
   *
   * Another interpretation is that it allows you to "bind" a name to the value (hence the name bind) inside and execute the following
   * procedure if you manage to successfully bind the value. Otherwise, short circuit.
   *
   * This method is (>>=) in Haskell, restricted to Option.
   * </p>
   *
   * @param <U> The type that the function provided <i> may </i> return.
   * @param f The action performed after a successful name binding.
   * @return A new Option.
   */
  public <U> Option<U> bind(Function<T, Option<U>> f) {
    return hasValue ? f.apply(val) : new Option<>();
  }

  /**
   * Applies a function to the value if it exists, otherwise returns the default value.
   *
   * @param <U> The type which the function returns.
   * @param f The function applied to the value (if it exists).
   * @param defaultVal The value returned if the Option is empty.
   * @return Either the defaultVal or the value of the Option, transformed by the function.
   */
  public <U> U maybe(Function<T, U> f, U defaultVal) {
    return hasValue ? f.apply(val) : defaultVal;
  }

  public String toString() {
    return hasValue ? "Value(" + val.toString() + ")" : "Empty()";
  }

  /**
   * Unwraps the value from an Option.
   * @param <T> The type of the value inside the Option.
   * @param opt The Option to unwrap.
   * @param defaultVal The value to return if the Option is empty.
   * @return The unwrapped value of the Option or the defaultVal provided.
   */
  public static <T> T fromOption(Option<T> opt, T defaultVal) {
    return opt.hasValue ? opt.val : defaultVal;
  }

}
