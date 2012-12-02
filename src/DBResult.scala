package com.paulasmuth.dpump

import scala.collection.mutable.LinkedList;

class DBResult(
  _head: Map[String, String],
  _data: LinkedList[List[String]]
) {
  val head = _head
  val data = _data
  var qtime: Long = 0
}