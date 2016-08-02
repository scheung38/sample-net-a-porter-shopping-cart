package com.netaporter.helper

import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar

/**
  * Created by minminsanjose on 02/08/2016.
  */
class ValidatorTest extends FunSuite with BeforeAndAfter with Matchers with MockitoSugar {

  val validator = Validator

  test("testing validator - validate range in between 3 to 9 ") {

    assert(validator.range(3, 9))

  }

  test("testing out of range - invalid range in between 9 and total of 5 ") {

    assert(!validator.range(9, 5))

  }

  test("testing out of range - minus int added ") {

    assert(!validator.range(-1, 5))
    assert(!validator.range(-1, -5))

  }

  test("Validator Mock") {

    trait ValidatorMock {

      val validatorMock = mock[Validator.type]
      assert(validatorMock.range(2, 10) == validator.range(2, 10))
      assert(validatorMock.range(2, 0) != validator.range(2, 10))
    }

  }
}