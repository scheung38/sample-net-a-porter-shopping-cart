package com.netaporter.helper

import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar

/**
  * Created by minminsanjose on 02/08/2016.
  */
class CSVReaderTest extends FunSuite with BeforeAndAfter with Matchers with MockitoSugar {

  val csvReader = CSVReader

  test("testing CSV import - Parsing the Items.csv data file") {

    assert(csvReader.loadFlatDatabase === List(List("1", "A-Short Sleeve Jumper", "£9.99"), List("2", "B-Shoulder Bag", "£9.99"), List("3", "C-Skinny Jeans", "£45.00"), List("4", "D-Leather Jeans", "£80"), List("5", "E-Leather Jacket", "£199.99"), List("6", "F-Wool Socks ", "£20.50"), List("7", "G-Piqué Polo shirt", " £50.55")))
    assert(csvReader.loadFlatDatabase.size === 7)

  }

  test("CSVReader Mock") {

    trait csvReaderMock {

      val csvReaderMock = mock[CSVReader.type]
      assert(csvReaderMock.loadFlatDatabase == csvReader.loadFlatDatabase)
    }

  }

}