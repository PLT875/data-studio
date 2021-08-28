package com.example
package readers

import com.databricks.spark.xml.XmlDataFrameReader

import org.apache.spark.sql.{DataFrame, SparkSession}

class PostLinksFileReader(implicit ss: SparkSession) extends XmlFileReader {

  // todo: define schema
  override def read(path: String): DataFrame = {
    ss.read.format("xml")
      .option("rowTag", "row")
      .xml(path)
  }
}
