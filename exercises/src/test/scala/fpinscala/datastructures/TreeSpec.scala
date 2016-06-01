package fpinscala.datastructures

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class TreeSpec extends FunSuite with TableDrivenPropertyChecks with Matchers {


  test("size counts the number of nodes (leaves and branches)") {

    // given
    val trees = Table(
      ("tree", "expectedSize"),
      (Branch(Leaf(1), Leaf(2)), 3),
      (Branch(Leaf(1), Branch(Leaf(2), Leaf(3))), 5),
      (Leaf(1), 1)
    )

    // when & then
    forAll(trees) { (tree, expectedSize) =>
      Tree.size(tree) should be(expectedSize)
    }
  }

  test("maximum returns the maximum element") {

    // given
    val trees = Table(
      ("tree", "max"),
      (Branch(Leaf(1), Leaf(10)), 10),
      (Branch(Leaf(30), Branch(Leaf(2), Leaf(3))), 30),
      (Leaf(-1), -1),
      (Branch(Leaf(3), Branch(Leaf(-2), Leaf(3))), 3)
    )

    // when & then
    forAll(trees) { (tree, max) =>
      Tree.maximum(tree) should be(max)
    }
  }

}
