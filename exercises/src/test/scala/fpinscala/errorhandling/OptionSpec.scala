package fpinscala.errorhandling

import org.scalatest.{FunSpec, Matchers}

import scala.util.Random
import scala.{Either => _, Option => _, Some => _}

class OptionSpec extends FunSpec with Matchers {

  describe("map") {

    it("transforms value if present") {
      // given
      val origin = Random.nextInt(100)
      val mapFn: (Int => Int) = _ + 1

      // when && then
      Some(origin).map(mapFn) should be(Some(mapFn(origin)))
    }

    it("returns empty option if value is not present") {
      // given
      val empty: Option[Int] = None

      // when && then
      empty.map(_ + 1) should be(None)
    }
  }

  describe("orElseGet") {

    it("returns default value when option is empty") {
      // given
      val empty: Option[Int] = None
      val expected = Random.nextInt(100)

      // when && then
      empty.getOrElse(expected) should be(expected)
    }

    it("returns origin value when option is not empty") {
      // given
      val origin = Random.nextInt(100)
      val nonEmpty = Some(origin)
      val default = origin + 100

      // when && then
      nonEmpty.getOrElse(default) should be(origin)
    }
  }

  describe("flatMap") {

    it("transforms value if present") {
      // given
      val origin = Random.nextInt(100)
      val mapFn: (Int => Option[Int]) = x => Some(x + 1)

      // when && then
      Some(origin).flatMap(mapFn) should be(mapFn(origin))
    }

    it("returns empty option if value is not present") {
      // given
      val empty: Option[Int] = None

      // when && then
      empty.flatMap(x => Some(x + 1)) should be(None)
    }
  }

  describe("orElse") {

    it("returns option of default value when option is empty") {
      // given
      val empty: Option[Int] = None
      val expected = Some(Random.nextInt(100))

      // when && then
      empty.orElse(expected) should be(expected)
    }

    it("returns origin option when option is not empty") {
      // given
      val origin = Random.nextInt(100)
      val nonEmpty = Some(origin)
      val default = Some(origin + 100)

      // when && then
      nonEmpty.orElse(default) should be(nonEmpty)
    }
  }

  describe("filter") {

    it("returns non empty option if predicate is fulfilled") {
      // given
      val validOption: Option[Int] = Some(Random.nextInt(100) + 10)

      // when && then
      validOption.filter(_ > 10) should be(validOption)
    }

    it("returns empty option if predicate is not fulfilled") {
      // given
      val validOption: Option[Int] = Some(Random.nextInt(100) + 10)

      // when && then
      validOption.filter(_ < 10) should be(None)
    }
  }

  describe("Option helpers") {

    describe("map2 - combines two Option values using a binary function") {

      val a = Random.nextInt(100)
      val b = Random.nextInt(100)
      val sumFn: (Int, Int) => Int = _ + _

      it("If either Option value is None, then the return mapped value is too") {
        Option.map2(Some(a), Some(b))(sumFn) should be(Some(sumFn(a, b)))
      }

      it("If first Option value is None, then the return None") {
        Option.map2(None, Some(b))(sumFn) should be(None)
      }

      it("If second Option value is None, then the return None") {
        Option.map2(Some(a), None)(sumFn) should be(None)
      }

      it("If both Option values are None, then the return None") {
        Option.map2(None, None)(sumFn) should be(None)
      }
    }

    describe("map2viaFlatMap - combines two Option values using a binary function") {

      val a = Random.nextInt(100)
      val b = Random.nextInt(100)
      val sumFn: (Int, Int) => Int = _ + _

      it("If either Option value is None, then the return mapped value is too") {
        Option.map2viaFlatMap(Some(a), Some(b))(sumFn) should be(Some(sumFn(a, b)))
      }

      it("If first Option value is None, then the return None") {
        Option.map2viaFlatMap(None, Some(b))(sumFn) should be(None)
      }

      it("If second Option value is None, then the return None") {
        Option.map2viaFlatMap(Some(a), None)(sumFn) should be(None)
      }

      it("If both Option values are None, then the return None") {
        Option.map2viaFlatMap(None, None)(sumFn) should be(None)
      }
    }

    describe("sequence - combines a list of Options into one Option containing a list of all the Some values in the original list") {
      val values = List(1, 2, 3)

      it("returns all the Some values when all Options are not empty") {
        Option.sequence(values.map(Some(_))) should be(Some(values))
      }

      it("returns None when at least one of the elements is empty") {
        Option.sequence(None :: values.map(Some(_))) should be(None)
      }

    }

    describe("sequence via traverse") {
      val values = List(1, 2, 3)

      it("returns all the Some values when all Options are not empty") {
        Option.sequenceViaTraverse(values.map(Some(_))) should be(Some(values))
      }

      it("returns None when at least one of the elements is empty") {
        Option.sequenceViaTraverse(None :: values.map(Some(_))) should be(None)
      }

    }

  }
}
