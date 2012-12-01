package com.paulasmuth.dpump

import java.util.Locale
import java.util.Date
import java.text.DateFormat
import scala.collection.mutable.HashMap;

object DPump{

  val VERSION = "v0.0.1"
  val CONFIG  = HashMap[Symbol,String]()

  var debug   = false
  var verbose = false

  def main(args: Array[String]) : Unit = {
    var n = 0

    while (n < args.length) {

      if((args(n) == "-l") || (args(n) == "--listen"))
        { CONFIG += (('listen, args(n+1))); n += 2 }

      else if((args(n) == "-d") || (args(n) == "--debug"))
        { debug = true; n += 1 }

      else if((args(n) == "-v") || (args(n) == "--verbose"))
        { verbose = true; n += 1 }

      else {
        println("error: invalid option: " + args(n) + "\n")
        return usage(false)
      }

    }

    val conn = new DBConnection("jdbc:mysql://localhost:3306/dawanda?user=root");
    val rslt = conn.execute("select version();")

    println(rslt.head, rslt.data);
  }

  def usage(head: Boolean = true) = {
    if (head)
      println("dpumpd " + VERSION + " (c) 2012 Paul Asmuth\n")

    println("usage: dpumpd [options]                                                    ")
    println("  -l, --listen      <port>    listen for clients on this tcp port          ")
    println("  -t, --timeout     <msecs>   connection idle timeout (default: 5000ms)    ")
    println("  -d, --debug                 debug mode                                   ")
    println("  -v, --verbose               verbose mode                                 ")
  }


  def log(msg: String) = {
    val now = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, Locale.FRANCE)
    println("[" + now.format(new Date()) + "] " + msg)
  }


  def error(msg: String) =
    log("[ERROR] " + msg)


  def debug(msg: String) =
    log("[DEBUG] " + msg)


  def exception(ex: Exception, fatal: Boolean) = {
    error(ex.toString)

    if (debug)
      for (line <- ex.getStackTrace)
        debug(line.toString)

    if (fatal)
      System.exit(1)
  }

}
