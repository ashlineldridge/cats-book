package ch01

import cats.kernel.Eq

object Equality extends App {

  import cats.syntax.eq._
  import cats.instances.string._
  import cats.instances.int._
  import cats.instances.option._

  implicit val catEq = Eq.instance[Cat] { (c1, c2) =>
    c1.name === c2.name && c1.age === c2.age && c1.colour === c2.colour
  }

  val cat1 = Cat("Garfield",   38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")
  println(s"Cat1 equals Cat2? ${cat1 === cat2}")
  println(s"Cat1 equals Cat1? ${cat1 === cat1}")

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]
  println(s"Optional cats are equal? ${optionCat1 === optionCat2}")
  println(s"Optional cats are equal? ${optionCat1 === optionCat1}")
}
