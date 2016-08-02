package com.netaporter.shop

import com.netaporter.helper.CSVReader

import scala.collection.mutable.ListBuffer

/**
  * Created by minminsanjose on 02/08/2016.
  */
sealed trait NetAPorterShop

object NetAPorterShop {

  private var list = ListBuffer[Int]()

  def loadProducts: List[List[Any]] = {
    CSVReader.loadFlatDatabase
  }

  def listProducts: String = {
    val str = for (item <- (loadProducts)) yield item.mkString("", " ", "")
    str.mkString(",\n")
  }

  /**
    * Add a product to the Basket
    */
  def addProductToBasket(productId: Int): ListBuffer[Int] = {

    list = list :+ (productId)
    list
  }

  /**
    * Remove a product from the Basket
    */
  def removeProductFromBasket(productId: Int): ListBuffer[Int] = {

    list = list diff ListBuffer(productId)
    list

  }

  /**
    * Return the total value of the products in the basket
    */
  def getTotal(): BigDecimal = {

    var total = Seq[BigDecimal]()
    val items = for (item <- loadProducts) yield item.toIndexedSeq(0) -> BigDecimal(item.toIndexedSeq(2).toString.replace("£", "").trim())
    val inventory = items.map { case (x) => (x._1, x._2) }

    list.toList match {
      case head :: _ => {
        for (item <- list) {
          total = total :+ inventory.toIndexedSeq(item - 1)._2
        }
        total.reduceLeft[BigDecimal](_ + _)

      }
      case Nil => 0.00
    }
  }
}