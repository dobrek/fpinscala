import fpinscala.datastructures._

val ex3: List[String] = Cons("a", Cons("b", Nil))

List.tail(List(1, 2, 3, 4))
List.setHead(List(1, 2, 3, 4), 0)
List.setHead(Nil, 1)
List.drop(List(1, 2, 3, 4), 1)
List.drop(Nil, 1)
List.dropWhile(List(1, 2, 3, 4), (a: Int) => a <= 2)
List.init(List(1, 2, 3, 4, 5))
List.init(Nil)

List.foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _))
List.foldRightViaFoldLeft(List(1, 2, 3), Nil: List[Int])(Cons(_, _))
List.length(List(1))

List.foldLeft(List(1, 2, 4), 0)(_ + _)
List.foldLeft(List(1, 2, 3, 4), 1)(_ * _)

List.appendViaFoldLeft(List(1, 2, 3), List(4, 5, 6))
List.appendViaFoldRight(List(1, 2, 3), List(4, 5, 6))


List.reverse(List(1, 2, 3))

List.concat(List(List(1, 2, 3), List(3, 4)))
List.map(List(1, 2, 3))(_ + 1)
List.filter(List(1, 2, 3, 4, 5, 6))(_ % 2 > 0)
List.flatMap(List(1, 2, 3))(i => List(i, i))
