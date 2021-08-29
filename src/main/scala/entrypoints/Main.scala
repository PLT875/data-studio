package com.example
package entrypoints

import readers.ReadersFactory

import com.example.writers.WritersFactory
import org.apache.spark.sql.SparkSession

object Main {

  def main(args: Array[String]) {
    val filePath = args(0)
    val ingestJob = args(1)

    implicit val spark = SparkSession.builder.appName(ingestJob).getOrCreate()

    val df = ReadersFactory.getReader(ingestJob).read(filePath)
    val writer = WritersFactory.getWriter(ingestJob)
    writer.write(df)

    spark.stop()
  }

}
