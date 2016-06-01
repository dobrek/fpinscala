package fpinscala.datastructures

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FunSuite , Matchers}

class TreeSpec extends FunSuite  with TableDrivenPropertyChecks with Matchers {


    test("size should count the number of nodes (leaves and branches) in a tree") {

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

}
