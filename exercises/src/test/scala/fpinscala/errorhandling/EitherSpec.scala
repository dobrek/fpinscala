package fpinscala.errorhandling

import fpinscala.errorhandling.Either.{sequence, traverse}
import org.scalatest.{FunSpec, Matchers}

import scala.util.Random

class EitherSpec extends FunSpec with Matchers {

  describe("map") {

    it("transforms value if present") {
      // given
      val origin = Random.nextInt(100)
      val mapFn: (Int => Int) = _ + 1

      // when && then
      Right(origin).map(mapFn) should be(Right(mapFn(origin)))
    }

    it("does not transform anything when the start value is left") {
      // given
      val either: Either[String, Int] = Left("error")

      // when && then
      either.map(_ + 1) should be(either)
    }
  }

  describe("flatMap") {

    it("transforms value if present") {
      // given
      val origin = Random.nextInt(100)
      val mapFn: (Int => Either[String, Int]) = x => Right(x + 1)

      // when && then
      Right(origin).flatMap(mapFn) should be(mapFn(origin))
    }

    it("returns left value if right value is not present") {
      // given
      val empty: Either[String, Int] = Left("empty")

      // when && then
      empty.flatMap(x => Right(x + 1)) should be(empty)
    }
  }

  describe("orElse") {

    it("returns option of default value when either has left value") {
      // given
      val empty: Either[String, Int] = Left("empty")
      val expected = Right(Random.nextInt(100))

      // when && then
      empty.orElse(expected) should be(expected)
    }

    it("returns origin right value when right value is not empty") {
      // given
      val origin = Random.nextInt(100)
      val nonEmpty = Right(origin)
      val default = Right(origin + 100)

      // when && then
      nonEmpty.orElse(default) should be(nonEmpty)
    }
  }

  describe("map2 - combines it with other Either values using a binary function") {

    val a = Random.nextInt(100)
    val b = Random.nextInt(100)
    val error = Random.nextString(10)
    val sumFn: (Int, Int) => Int = _ + _

    it("If either value is None, then the return mapped value is too") {
      Right(a).map2(Right(b))(sumFn) should be(Right(sumFn(a, b)))
    }

    it("If either value is Left, then the return Left") {
      Left(error).map2(Right(b))(sumFn) should be(Left(error))
    }

    it("If second either value is Left, then the return type is Left") {
      Right(a).map2(Left(error))(sumFn) should be(Left(error))
    }

    it("If both Eiters values are Left, then the return the first left") {
      Left(error).map2(Left(Random.nextString(10)))(sumFn) should be(Left(error))
    }
  }

  describe("Either companion object") {

    describe("sequence - combines a list of Eithers into one Either returns the first error that’s encountered, if there is one") {
      val values = List(1, 2, 3)

      it("returns all the values when all Eithers have 'right' value") {
        sequence(values.map(Right(_))) should be(Right(values))
      }

      it("returns first 'left' when at least one of the elements is 'left'") {
        sequence(Right(1) :: values.map(Left(_))) should be(Left(values.head))
      }
    }

    describe("traverse") {

      val values = List(1, 2, 3)
      val mapFn: (Int) => Int = _ + 1

      it("returns as right with all values when all Eithers become 'right'") {
        traverse(values)(a => Right(mapFn(a))) should be(Right(values.map(mapFn)))
      }

      it("returns as 'left' when at least one of the result is 'left'") {
        traverse(values)(Left(_)) should be(Left(values.head))
      }
    }

  }

}
