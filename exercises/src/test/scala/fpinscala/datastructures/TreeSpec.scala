package fpinscala.datastructures

import fpinscala.datastructures.Tree.size
import org.scalatest.FunSpec

class TreeSpec extends FunSpec {

  describe("A Tree") {

    it("should have size ") {
      val tree: Tree[Int] = Branch(Leaf(1), Leaf(2))
      assert(size(tree) == 3)
    }
  }
}
