package fpinscala.errorhandling

import org.scalatest.{FunSpec, Matchers}

import scala.util.Random

/**
  * Created by dkropielnicki on 2016-06-09.
  */
class EitherSpec extends FunSpec with Matchers {

  describe("map") {

    it("transforms right value if present") {
      // given
      val origin = Random.nextInt(100)
      val mapFn: (Int => Int) = _ + 1

      // when && then
      Right(origin).map(mapFn) should be(Right(mapFn(origin)))
    }

    it("returns left value if right value is not present") {
      // given
      val origin = Random.nextInt(100)
      val left: Either[Int, Int] = Left(origin)

      // when && then
      left.map(_ + 1) should be(Left(origin))
    }
  }

}
