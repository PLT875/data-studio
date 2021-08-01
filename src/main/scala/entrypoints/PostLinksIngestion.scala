package org.data.studio
package entrypoints

import readers.PostLinksFileReader

import org.apache.spark.sql.{SaveMode, SparkSession}

import java.util.Properties

object PostLinksIngestion {
  def main(args: Array[String]) {
    implicit val spark = SparkSession.builder.appName("Post Links Ingestion").getOrCreate()
    val postLinksFileReader = new PostLinksFileReader()
    val df = postLinksFileReader.readPostLinksFile("data/postLinksTest.xml")

    val prop = new Properties()
    prop.setProperty("user", "spark")
    prop.setProperty("password", "123")
    prop.put("driver", "org.postgresql.Driver")

    df.show()
    println(df.schema)

    df.write
      .mode(SaveMode.Overwrite)
      .jdbc("jdbc:postgresql://postgresql:5432/stackoverflow", "post_links", prop)


    spark.stop()
  }
}
