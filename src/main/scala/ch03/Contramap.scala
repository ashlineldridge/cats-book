package ch03

object Contramap {

  trait Printable[A] { self =>
    def format(a: A): String
    def contramap[B](f: B => A): Printable[B] =
      new Printable[B] {
        def format(b: B) = self.format(f(b))
      }
  }

  def format[A](a: A)(implicit p: Printable[A]): String =
    p.format(a)
}

case class Box[A](value: A)

object ContramapApp extends App {

  import ch03.Contramap._

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }

  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }

  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] =
    p.contramap[Box[A]](_.value)

  println(format("hello"))
  println(format(true))
  println(format(Box("hello")))
  println(format(Box(true)))

}
