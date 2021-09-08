package com.example
package entrypoints

import readers.UsersFileReader
import writers.{UsersDbWriter}

import org.apache.spark.sql.SparkSession

object UsersIngestion {

  def main(args: Array[String]) {
    implicit val spark = SparkSession.builder.appName("Users Ingestion").getOrCreate()
    val usersFileReader = new UsersFileReader()
    val filePath = args(0)
    val df = usersFileReader.read(filePath)
    // df.createOrReplaceTempView("users")

    val usersDbWriter = new UsersDbWriter()
    usersDbWriter.writeToTable(df);

    spark.stop()
  }

}
