package com.company.datastructure;

import java.util.function.Function;

/**
 * <p>
 * An Option is an object which has two states: empty and non-empty.
 * Empty Options don't contain a value while non-empty Options do. Working with
 * Options makes it more difficult to hit NullPointerException errors,
 * assuming that you never try and get out of the Option context unsafely (ie getVal).
 * Option allows for "safe nulls" and allows you to perform actions on values (which may not exist)
 * without having to check whether they exist. This cleans up a lot of boilerplate code that try
 * to test for null.
 * </p>
 *
 * @author Jade Piramide <jadepiramidepogi@gmail.com>
 * @param <T> The type held inside the Option.
 */
public class Option<T> {

  private final boolean hasValue;
  private final T val;

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
   * Unwraps the value from an Option.
   * @param <T> The type of the value inside the Option.
   * @param defaultVal The value to return if the Option is empty.
   * @return The unwrapped value of the Option or the defaultVal provided.
   */
  public T fromOption(T defaultVal) {
    return hasValue
      ? val
      : defaultVal;
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
  public <U> Option<U> map(Function<T, U> f) {
    return bind(f.andThen(Option::new));
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
    return hasValue
      ? f.apply(val)
      : new Option<>();
  }

  /**
   * Applies a function to the value if it exists, otherwise returns the default value.
   * @param <U> The type which the function returns.
   * @param f The function applied to the value (if it exists).
   * @param defaultVal The value returned if the Option is empty.
   * @return Either the defaultVal or the value of the Option, transformed by the function.
   */
  public <U> U maybe(Function<T, U> f, U defaultVal) {
    return hasValue
      ? f.apply(val)
      : defaultVal;
  }

  /**
   * Combines two Option<T> instances like combining booleans using &&.
   * Returns the second Option if the first is non-empty, returns an empty Option otherwise.
   * @param other
   * @return The second Option if first is non-empty, empty Option otherwise.
   */
  public Option<T> and(Option<T> other) {
    return hasValue
      ? other
      : this;
  }

  /**
   * Combines two Option<T> instances like combining booleans using ||.
   * Returns the first Option if it's non-empty, returns the second Option otherwise.
   * @param other
   * @return The first Option if non-empty, the second otherwise.
   */
  public Option<T> or(Option<T> other) {
    return hasValue
      ? this
      : other;
  }

  public String toString() {
    return hasValue
      ? "Value(" + val.toString() + ")"
      : "Empty()";
  }

}
