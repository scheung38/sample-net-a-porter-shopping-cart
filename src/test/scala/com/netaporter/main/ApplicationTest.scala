package com.netaporter.main

import com.netaporter.shop.NetAPorterShop
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

import scala.collection.mutable.ListBuffer

/**
  * Created by minminsanjose on 02/08/2016.
  */
class ApplicationTest extends FeatureSpec with GivenWhenThen with Matchers {

  info("I want to be able to load products from CSV file")
  info("And I want to be able to show which products can be purchased as a list")
  info("And I want to be able to add product to basket by ID")
  info("And I want to get the total amount for the added product")
  info("And I should be able to remove product by ID")
  info("Then I want to get the total amount for an empty basket")

  feature("Application object") {

    scenario("Create an object with list of products") {

      Given("a CSV file is loaded and a product list created")
      val shop = NetAPorterShop
      val list = shop.loadProducts
      list should be(List(List("1", "A-Short Sleeve Jumper", "£9.99"), List("2", "B-Shoulder Bag", "£9.99"), List("3", "C-Skinny Jeans", "£45.00"), List("4", "D-Leather Jeans", "£80"), List("5", "E-Leather Jacket", "£199.99"), List("6", "F-Wool Socks ", "£20.50"), List("7", "G-Piqué Polo shirt", " £50.55")))

      Then("the human readable list should created")
      val screenList = shop.listProducts
      screenList should be("1 A-Short Sleeve Jumper £9.99,\n2 B-Shoulder Bag £9.99,\n3 C-Skinny Jeans £45.00,\n4 D-Leather Jeans £80,\n5 E-Leather Jacket £199.99,\n6 F-Wool Socks  £20.50,\n7 G-Piqué Polo shirt  £50.55")

      Then("Adding a few products to basket")
      shop.addProductToBasket(1) should be(ListBuffer(1, 5, 1)) //Assuming we already have a few products in basket (pre added via NetAPorterShopTest) and we run test in CI
      //com.netaporter.shop.addProductToBasket(1) should be(ListBuffer(1))

      Then("Remove product from basket")
      shop.removeProductFromBasket(1) should be(ListBuffer(5, 1))
      //com.netaporter.shop.removeProductFromBasket(1) should be(ListBuffer())

      Then("Get total")
      shop.getTotal() should be(209.98)


      Then("Get total - empty basket")
      shop.getTotal() should be(0.00)

    }
  }
}
