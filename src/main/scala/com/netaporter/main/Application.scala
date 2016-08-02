package com.netaporter.main

import java.util.Scanner

import com.netaporter.helper.Validator
import com.netaporter.shop.NetAPorterShop

/**
  * Created by minminsanjose on 02/08/2016.
  */
object Application extends App {


  println("************************************")
  println("* Welcome to the Net-A-Porter Shop *")
  println("************************************")
  println("Enter \"Q\" to Quit")
  println("Enter \"add <ProductId>\" to add to basket")
  println("Enter \"remove <ProductId>\" to remove from basket")
  println("Enter \"list\" to show a list of products in the inventory")
  println("Enter \"total\" to show the total price of the basket")

  val scanner: Scanner = new Scanner(System.in)
  val shop = NetAPorterShop
  val items = shop.loadProducts
  var productId = 0

  while (true) {

    val inputValue: String = scanner.next

    if (inputValue.startsWith("add")) {

      if (scanner.hasNextInt()) {

        productId = scanner.nextInt()

        if (Validator.range(productId, items.size)) {

          shop.addProductToBasket(productId)
          println("Item #" + productId + " was added to your basket")
        }
        else {
          println(errorMsg(items.size))
        }
      }

    }
    else if (inputValue.startsWith("remove")) {

      if (scanner.hasNextInt()) {

        productId = scanner.nextInt()

        if (Validator.range(productId, items.size)) {

          shop.removeProductFromBasket(productId)
          println("Item #" + productId + " has been removed from your basket")
        }
        else {
          println(errorMsg(items.size))
        }
      }

    }
    else if (inputValue.startsWith("list")) {
      println(shop.listProducts)
    }
    else if (inputValue.startsWith("total")) {
      println("£" + shop.getTotal())
    }
    else if ("Q".equalsIgnoreCase(inputValue)) {
      System.out.println("Thanks for shopping at Net-a-Porter!")
      System.exit(0)
    }
  }

  def errorMsg(range: Int): String = {
    s"Wrong ProductId entered. You can choose in between 1 to " + range
  }

}
