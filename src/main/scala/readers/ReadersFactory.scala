package com.example
package readers

import org.apache.spark.sql.SparkSession

object ReadersFactory {

  def getReader(reader: String)(implicit ss: SparkSession) : XmlFileReader = {
    reader match {
      case "postLinks" => new PostLinksFileReader
      case "users" => new UsersFileReader
      case _ => throw new IllegalArgumentException
    }
  }

}
