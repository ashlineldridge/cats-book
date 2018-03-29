package ch02

import cats.Monoid
import cats.instances.all._
import cats.syntax.foldable._

case class Order(totalCost: Double, quantity: Double)

object SuperAdder {

  def add[A: Monoid](items: List[A]): A =
    items.combineAll
}

object SuperAdderApp extends App {

  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    def empty: Order = Order(0.0, 0.0)
    def combine(x: Order, y: Order): Order =
      Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
  }

  val orders = List(Order(25.99, 3.0), Order(4.99, 2.0))
  println(SuperAdder.add(orders))
}
