package com.example
package entrypoints

import readers.PostLinksFileReader
import writers.PostLinksDbWriter

import org.apache.spark.sql.SparkSession

// todo: could be more generic
object PostLinksIngestion {

  def main(args: Array[String]) {
    implicit val spark = SparkSession.builder.appName("Post Links Ingestion").getOrCreate()
    val postLinksFileReader = new PostLinksFileReader()
    val filePath = args(0)
    val df = postLinksFileReader.read(filePath)
    // df.createOrReplaceTempView("post_links")

    val postLinksDbWriter = new PostLinksDbWriter()
    postLinksDbWriter.writeToTable(df);

    spark.stop()
  }

}
