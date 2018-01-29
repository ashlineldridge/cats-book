package ch02

import cats.{Monoid, Semigroup}

class Monoids {

  object boolean {
    implicit val booleanOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {
      def empty: Boolean = true
      def combine(x: Boolean, y: Boolean): Boolean = x || y
    }

    implicit val booleanAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
      def empty: Boolean = false
      def combine(x: Boolean, y: Boolean): Boolean = x && y
    }

    implicit val booleanXorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
      def empty: Boolean = false
      def combine(x: Boolean, y: Boolean): Boolean = (x && !y) || (!x && y)
    }

    implicit val booleanXnorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
      def empty: Boolean = true
      def combine(x: Boolean, y: Boolean): Boolean = (!x && !y) || (x && y)
    }
  }

  object set {
    implicit def setUnionMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
      def empty: Set[A] = Set.empty
      def combine(x: Set[A], y: Set[A]): Set[A] = x union y
    }

    implicit def setIntersectSemigroup[A]: Semigroup[Set[A]] = new Semigroup[Set[A]] {
      def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
    }

    implicit def setSymmetricDifferenceMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
      def empty: Set[A] = Set.empty
      def combine(x: Set[A], y: Set[A]): Set[A] = (x diff y) union (y diff x)
    }
  }
}
