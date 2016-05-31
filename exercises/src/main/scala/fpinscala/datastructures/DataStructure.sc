import fpinscala.datastructures.List.{foldRight, length, sum}
import fpinscala.datastructures._

val ex3: List[String] = Cons("a", Cons("b", Nil))

val x = List(1, 2, 3, 4, 5) match {
  case Cons(x, Cons(2, Cons(4, _))) => x
  case Nil => 42
  case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
  case Cons(h, t) => h + sum(t)
  case _ => 101
}

List.tail(List(1, 2, 3, 4))
List.setHead(List(1, 2, 3, 4), 0)
List.setHead(Nil, 1)
List.drop(List(1, 2, 3, 4), 1)
List.drop(Nil, 1)
List.dropWhile(List(1, 2, 3, 4), (a: Int) => a <= 2)
List.init(List(1, 2, 3, 4, 5))
List.init(Nil)

foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _))
List.foldRightViaFoldLeft(List(1, 2, 3), Nil: List[Int])(Cons(_, _))
length(List(1))

List.foldLeft(List(1, 2, 4), 0)(_ + _)
List.foldLeft(List(1, 2, 3, 4), 1)(_ * _)

List.appendViaFoldLeft(List(1, 2, 3), List(4, 5, 6))
List.appendViaFoldRight(List(1, 2, 3), List(4, 5, 6))


List.reverse(List(1, 2, 3))

List.concat(List(List(1, 2, 3), List(3, 4)))
