package fpinscala.laziness

import org.scalatest.{FlatSpec, Matchers}

class StreamSpec extends FlatSpec with Matchers {

  "toList" should "covers Stream into a List" in {
    Stream(1, 2, 3).toList2 should be(List(1, 2, 3))
  }

  it should "covers empty Stream into empty List" in {
    Stream().toList should be(List())
  }
}
