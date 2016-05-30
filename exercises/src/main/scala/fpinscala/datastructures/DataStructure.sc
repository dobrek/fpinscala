import fpinscala.datastructures.List.sum
import fpinscala.datastructures._

val ex3: List[String] = Cons("a", Cons("b", Nil))
List(1, 2, 3, 4) match {
  case Cons(x, Cons(2, Cons(3, _))) => x
}
val x = List(1, 2, 3, 4, 5) match {
  case Cons(x, Cons(2, Cons(4, _))) => x
  case Nil => 42
  case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
  case Cons(h, t) => h + sum(t)
  case _ => 101
}

val t = List.tail(List(1, 2, 3, 4))