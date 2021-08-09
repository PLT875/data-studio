package com.example
package entrypoints

import readers.PostLinksFileReader
import writers.PostLinksDbWriter

import org.apache.spark.sql.SparkSession

object PostLinksIngestion {
  def main(args: Array[String]) {
    implicit val spark = SparkSession.builder.appName("Post Links Ingestion").getOrCreate()
    val postLinksFileReader = new PostLinksFileReader()
    val filePath = args(0)
    val df = postLinksFileReader.readPostLinksFile(filePath)
    println(df.schema)

    val postLinksDbWriter = new PostLinksDbWriter()
    postLinksDbWriter.writeToTable(df);

    spark.stop()
  }
}
