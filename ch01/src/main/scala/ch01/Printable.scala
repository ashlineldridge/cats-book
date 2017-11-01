package ch01

import cats.Show

final case class Cat(name: String, age: Int, colour: String)

sealed trait Printable[A] {
  def format(value: A): String
}

object Printable {
  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)

  def print[A: Printable](value: A): Unit =
    println(format(value))
}

object PrintableSyntax {
  implicit class PrintOps[A](value: A) {
    def format(implicit p: Printable[A]): String =
      p.format(value)
    def print(implicit p: Printable[A]): Unit =
      println(format)
  }
}

object PrintableInstances {
  implicit val stringPrintable = new Printable[String] {
    def format(value: String): String = value
  }
  implicit val intPrintable = new Printable[Int] {
    def format(value: Int): String = value.toString
  }
  implicit val catPrintable = new Printable[Cat] {
    def format(c: Cat): String = {
      val name   = Printable.format(c.name)
      val age    = Printable.format(c.age)
      val colour = Printable.format(c.colour)
      s"${name} is a ${age} year-old ${colour} cat."
    }
  }
}

object SimpleSyntax extends App {
  import PrintableInstances._

  val cat = Cat("Toby", 5, "white")
  Printable.print(cat)
}

object BetterSyntax extends App {
  import PrintableInstances._
  import PrintableSyntax._

  val cat = Cat("Toby", 5, "white")
  cat.print
}

object ShowSyntax extends App {

  import cats.instances.string._
  import cats.instances.int._
  import cats.syntax.show._

  implicit val catShow = Show.show[Cat](c => s"${c.name.show} is a ${c.age.show} year-old ${c.colour.show} cat.")

  val cat = Cat("Toby", 5, "white")
  println(cat.show)
}
