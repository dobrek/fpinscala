import fpinscala.datastructures.List._
import fpinscala.datastructures._

val ex3: List[String] = Cons("a", Cons("b", Nil))

tail(List(1, 2, 3, 4))
setHead(List(1, 2, 3, 4), 0)
setHead(Nil, 1)
drop(List(1, 2, 3, 4), 1)
drop(Nil, 1)
dropWhile(List(1, 2, 3, 4), (a: Int) => a <= 2)
init(List(1, 2, 3, 4, 5))
init(Nil)

foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _))
foldRightViaFoldLeft(List(1, 2, 3), Nil: List[Int])(Cons(_, _))
length(List(1))

foldLeft(List(1, 2, 4), 0)(_ + _)
foldLeft(List(1, 2, 3, 4), 1)(_ * _)

appendViaFoldLeft(List(1, 2, 3), List(4, 5, 6))
appendViaFoldRight(List(1, 2, 3), List(4, 5, 6))


reverse(List(1, 2, 3))

concat(List(List(1, 2, 3), List(3, 4)))
map(List(1, 2, 3))(_ + 1)
filter(List(1, 2, 3, 4, 5, 6))(_ % 2 > 0)
flatMap(List(1, 2, 3))(i => List(i, i))
filterViaFlatMap(List(1, 2, 3, 4, 5, 6))(_ % 2 > 0)

zipWith(List(1, 2, 3), List(4, 5, 6))(_ + _)

hasSubsequence(List(1, 2, 3, 4), List(1, 2, 3))
!hasSubsequence(List(1, 2), List(1, 2, 3))
hasSubsequence(List(1, 2, 3, 4), List(2, 3))
hasSubsequence(List(0, 1, 2, 3, 4, 5), List(2, 3))
hasSubsequence(List(1, 2, 3, 4), List(4))
!hasSubsequence(List(1, 2, 3, 4), List(1, 5))
!hasSubsequence(Nil: List[Int], List(1, 5))
hasSubsequence(List(1, 2, 3, 4), Nil: List[Int])