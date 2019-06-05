package com.company.functional;

import java.util.function.Function;

/**
 * <p>
 * A Functor is a mapping between categories. A map method exists that 'lifts'
 * the given function and enables it to operate on lifted types. The Functor is
 * defined to be a map where F : A -> F(A). This interface is an Endofunctor (a
 * Functor which maps to the same category)
 *
 * A Functor is a structure that can contain a value and gives the ability to
 * alter the value within without changing the structure itself. This is
 * completely identical to the Functor type class in Haskell.
 *
 * Due to Java's type system lacking the ability to properly express higher-kinded
 * types, this interface exists only as a marker interface (similar to Cloneable).
 * <b> It is not safe to rely on classes which implement Functor if you did not write
 * the source yourself. </b> Due to this interface being empty, the compiler doesn't fuss
 * whether you've implemented the methods or not, due to this interface <i> not </i>
 * having any methods in the first place. The interface still contains what the methods
 * of Functor are (commented out), and documentation associated with each method.
 *
 * ? If you want to properly implement Functor<E>, make sure that your class meets the following
 * ? requirements:
 * ? 1) It must be generic.
 * ? 2) Your class must have a method named map which is typed as such
 * ?    2i) public <F> Class<F> map(Function<E, F> f)
 * ? 3) This map method must follow the Functor laws:
 * ?    3i)  (Identity law) functor.fmap(x -> x) ~= functor
 * ?    3ii) (Composition law) functor.fmap(f.andThen(g)) ~= functor.fmap(f).fmap(g)
 *      * NOTE: ~= means equivalence in value, but not necessarily identity. x ~= y implies that
 *      * x and y <i> can be </i> regarded and treated like the same object (due to having the same contents)
 *      * but not necessarily be equal in identity (i.e x == y may result in false).
 *
 * ! Only classes which meet the above requirements can be recognized as properly implementing
 * ! this interface.
 *
 * ! <b> This interface doesn't guarantee a properly implemented Functor. <b>
 * </p>
 *
 * @author Jade Piramide <jadepiramidepogi@gmail.com>
 * @param <E> The type contained within the Functor structure.
 */
public interface Functor<E> {
  // Class<E> refers to the Class implementing the Functor, not reflect's Class.

  /**
   * Transforms the value inside a Class<T>
   *
   * <p>
   * Allows for the transformation of the value inside a Class<T>, without altering
   * the original structure, whatever that structure may be. Implementations of
   * map are expected to follow the Functor laws, listed in the documentation of the
   * interface itself.
   *
   * This function does not mutate the original object.
   * </p>
   *
   * @param <U> The new type which the Class will hold after transforming the value.
   * @param f The function responsible for transforming the value.
   * @return A new Class<U> which contains the transformed value.
   */
  // public <F> Class<F> map(Function<E, F> f);

}
