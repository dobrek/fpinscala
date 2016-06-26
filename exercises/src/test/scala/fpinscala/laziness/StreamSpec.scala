package fpinscala.laziness

import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class StreamSpec extends FlatSpec with Matchers {

  "toList" should "covers Stream into a List" in {
    Stream(1, 2, 3).toList2 should be(List(1, 2, 3))
  }

  it should "covers empty Stream into empty List" in {
    Stream().toList should be(List())
  }

  "take" should "create a Stream from n first elements of the original once" in {
    // given
    val values = List(0, 1, 1, 2, 3, 5, 8, 13)
    val n = 1 + Random.nextInt(values.length - 2)

    // when & then
    Stream(values: _*).take(n).toList should be(values.take(n))
  }

  it should "create a empty Stream from the empty one" in {
    Stream().take(Random.nextInt(10)) should be(Empty)
  }

  it should "create a one size Stream" in {
    Stream(2, 3, 4).take(1).toList should be(Stream(2).toList)
  }

  "drop" should "create a Stream by skipping n first elements of the original once" in {
    // given
    val values = List(0, 1, 1, 2, 3, 5, 8, 13)
    val n = 1 + Random.nextInt(values.length - 2)

    // when & then
    Stream(values: _*).drop(n).toList should be(values.drop(n))
  }

  "takeWhile" should "returning all starting elements of a Stream that match the given predicate" in {
    // given
    val values = List(0, 1, 1, 2, 3, 5, 8, 13)
    val predicate: Int => Boolean = _ < 5

    // when & then
    Stream(values: _*).takeWhile(predicate).toList should be(values.filter(predicate))
  }

  "takeWhileFoldRight" should "returning all starting elements of a Stream that match the given predicate" in {
    // given
    val values = List(0, 1, 1, 2, 3, 5, 8, 13)
    val predicate: Int => Boolean = _ < 5

    // when & then
    Stream(values: _*).takeWhileViaFoldRight(predicate).toList should be(values.filter(predicate))
  }

  "forAll" should "return true when all elements in the Stream match a given predicatee" in {
    // when & then
    Stream(0, 2, 3, 4).forAll(_ < 5) should be(true)
  }

  it should "return false when at least one element in the Stream does not match a given predicatee" in {
    // when & then
    Stream(1, 2, 5).forAll(_ < 4) should be(false)
  }

  ignore should "use lazy evaluation" in {
    // when & then
    Stream(1, 2, 5, ???).forAll(_ < 4) should be(false)
  }

  "optionHead" should "take first element from stream as Option" in {
    // when & then
    Stream(1, 2, 3).headOption should be(Option(1))
  }

  "map" should "transform stream into another stream using mapping function " in {
    // given
    val values = List(1, 2, 3, 4)
    val mapFn: Int => Int = _ + 1

    // when & then
    Stream(values: _*).map(mapFn).toList should be(values.map(mapFn))
  }

}
