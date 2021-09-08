package com.example
package writers

import org.apache.spark.sql.SparkSession

object WritersFactory {

  def getWriter(writer : String) : DbWriter = {
    writer match {
      case "postLinks" => new PostLinksDbWriter
      case "user" => new UsersDbWriter
      case _ => throw new IllegalArgumentException
    }
  }


}
