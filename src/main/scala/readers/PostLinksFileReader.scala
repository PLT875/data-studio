package com.example
package readers

import com.databricks.spark.xml.XmlDataFrameReader
import org.apache.spark.sql.types.{LongType, StructField, StructType, TimestampType}

import org.apache.spark.sql.{DataFrame, SparkSession}

class PostLinksFileReader(implicit ss: SparkSession) {

  def readPostLinksFile(path: String): DataFrame = {
    val schema = new StructType(
      Array(
        StructField("_CreationDate", TimestampType),
        StructField("_Id", LongType),
        StructField("_LinkTypeId", LongType),
        StructField("_PostId", LongType),
        StructField("_RelatedPostId", LongType)
      ))

    ss.read.format("xml")
      //.schema(schema)
      .option("rowTag", "row")
      .xml(path)
  }

}
