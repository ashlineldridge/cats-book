package ch01

import simulacrum.typeclass

// This file is an example of using Simulacrum to remove the boilerplate from Printable.scala
// Much nicer!

@typeclass trait Stringable[A] {
  def format(value: A): String
}

// Pattern seems to be have a package called syntax and then a file in there called stringable.scala
object syntax {
  object stringable extends Stringable.ToStringableOps
}

// Pattern seems to be have a package called instances and then a file in there called cat.scala which defines
// implicit instances of typeclasses for Cat objects
object instances {
  implicit val catStringable = new Stringable[Cat] {
    def format(c: Cat): String = {
      s"${c.name} is a ${c.age} year-old ${c.colour} cat."
    }
  }
}

object CatApp extends App {

  import syntax.stringable._
  import instances._

  println(Cat("Ashlin", 5, "Black").format)

}
