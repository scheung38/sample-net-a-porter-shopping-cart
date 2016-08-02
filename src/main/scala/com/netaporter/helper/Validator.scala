package com.netaporter.helper

sealed trait Validator

/**
  * Created by minminsanjose on 02/08/2016.
  */
object Validator {

  def range(current: Int, total: Int): Boolean = current match {
    case x if 1 until total+1 contains x => true
    // case validateRange(current, total: Int) if current == total => true
    case _ => false
  }

}