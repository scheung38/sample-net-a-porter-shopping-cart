package com.netaporter.shop

import com.netaporter.helper.CSVReader
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

import scala.collection.mutable.ListBuffer

/**
  * Created by minminsanjose on 02/08/2016.
  */
class NetAPorterShopTest extends FunSuite with BeforeAndAfter with Matchers with MockitoSugar {

  val csvReader = CSVReader
  val shop = NetAPorterShop

  test("test load products") {

    assert(shop.loadProducts === (List(List("1", "A-Short Sleeve Jumper", "£9.99"), List("2", "B-Shoulder Bag", "£9.99"), List("3", "C-Skinny Jeans", "£45.00"), List("4", "D-Leather Jeans", "£80"), List("5", "E-Leather Jacket", "£199.99"), List("6", "F-Wool Socks ", "£20.50"), List("7", "G-Piqué Polo shirt", " £50.55"))))
    assert(csvReader.loadFlatDatabase == shop.loadProducts)
    assert(shop.loadProducts.size === 7)

  }

  test("test listing products") {

    assert(shop.listProducts == s"1 A-Short Sleeve Jumper £9.99,\n2 B-Shoulder Bag £9.99,\n3 C-Skinny Jeans £45.00,\n4 D-Leather Jeans £80,\n5 E-Leather Jacket £199.99,\n6 F-Wool Socks  £20.50,\n7 G-Piqué Polo shirt  £50.55")

  }

  test("test add product into basket") {

    shop.addProductToBasket(1)
    shop.addProductToBasket(5)
    shop.addProductToBasket(2)
    shop.addProductToBasket(7)

    assert(shop.addProductToBasket(7) === (ListBuffer(1, 5, 2, 7, 7)))
    assert(shop.addProductToBasket(4).size === 6)

  }

  test("test remove product from basket") {

    shop.removeProductFromBasket(1)
    shop.removeProductFromBasket(5)

    assert(shop.removeProductFromBasket(7) === (ListBuffer(2, 7, 4)))

    shop.removeProductFromBasket(2)
    shop.removeProductFromBasket(7)

    assert(shop.removeProductFromBasket(4).size === 0)

  }

  test("test total") {

    shop.addProductToBasket(1)
    shop.addProductToBasket(5)

    assert(shop.getTotal() === 209.98)

    shop.removeProductFromBasket(1)
    shop.removeProductFromBasket(5)

  }

  test("test total - empty basket") {

    assert(shop.getTotal() === 0.00)

  }

  test("NetAPorterShop Mock") {

    trait NetAPorterShopMock {

      val shopMock = mock[NetAPorterShop.type]

      assert(shopMock.addProductToBasket(2) == shop.addProductToBasket(2))
      assert(shopMock.getTotal() == shop.getTotal())
      assert(shopMock.removeProductFromBasket(2) == shop.removeProductFromBasket(2))
      assert(shopMock.getTotal() == shop.getTotal())

    }

  }
}