package com.paulasmuth.dpump

import scala.collection.mutable.ListBuffer;

class Instruction {
  var name : String = null
  var args = ListBuffer[String]()
  var next = List[Instruction]()
  var prev : Instruction = null

  var job : DPump.db_pool.Job = null
  var ready = false

  var relation : ResourceRelation = null
  var relation_args = List[Int]()

  def inspect(lvl: Int) : Unit = 
    DPump.log_debug((" " * (lvl*2)) + "> name: " + name + ", args: " + (
      if (args.size > 0) args.mkString(", ") else "none"))
}
