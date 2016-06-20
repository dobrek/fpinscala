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

}
