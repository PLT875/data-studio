package org.data.studio.entrypoints

import org.apache.spark.sql.SparkSession

object Test {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
    val data = Array(1, 2, 3, 4, 5)
    val result = data.reduce((a, b) => a + b)
    println(s"Result: $result")
    spark.stop()
  }
}
