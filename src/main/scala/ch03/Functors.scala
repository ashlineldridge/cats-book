package ch03

import cats.Functor
import cats.instances.list._
import cats.instances.option._

object Functors {

  val list1 = List(1, 2, 3)
  val list2 = Functor[List].map(list1)(_ * 2)

  val option1 = Option(123)
  val option2 = Functor[Option].map(option1)(_.toString)

  val func = (x: Int) => x + 1
  val liftedFunc = Functor[Option].lift(func)

  liftedFunc(Option(1))

  implicit val treeFunctor = new Functor[Tree] {
    def map[A, B](t: Tree[A])(f: A => B): Tree[B] =
      t match {
        case Leaf(v)      => Leaf(f(v))
        case Branch(l, r) => Branch(map(l)(f), map(r)(f))
      }
  }
}


object TreeApp extends App {

  import cats.syntax.functor._
  import Functors._
  import Tree._

  val tree = branch(leaf(1), branch(leaf(2), leaf(3)))

  val newTree = tree.map(_ + 5)
  println(newTree)
}