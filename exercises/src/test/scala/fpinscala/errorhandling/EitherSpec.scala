package fpinscala.errorhandling

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


}
