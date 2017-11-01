package ch01

import simulacrum.typeclass

// This file is an example of using Simulacrum to remove the boilerplate from Printable.scala
// Much nicer!

@typeclass trait Stringable[A] {
  def format(value: A): String
}

object Stringable {
  object syntax extends Stringable.ToStringableOps
}

object CatInstances {
  implicit val catPrintable = new Stringable[Cat] {
    def format(c: Cat): String = {
      s"${c.name} is a ${c.age} year-old ${c.colour} cat."
    }
  }
}

object CatApp extends App {

  import Stringable.syntax._
  import CatInstances._

  println(Cat("Ashlin", 5, "Black").format)

}
