package com.example
package readers

import com.databricks.spark.xml.XmlDataFrameReader
import org.apache.spark.sql.{DataFrame, SparkSession}

class UsersFileReader(implicit ss: SparkSession) {

  // todo: define schema
  def readUsersFile(path: String): DataFrame = {
    ss.read.format("xml")
      .option("rowTag", "row")
      .xml(path)
  }

}
