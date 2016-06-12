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

}
