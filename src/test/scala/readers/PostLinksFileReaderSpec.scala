package com.example
package readers

import org.apache.spark.sql.SparkSession
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

import java.util.TimeZone

class PostLinksFileReaderSpec extends AnyFlatSpec with Matchers {

  TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
  implicit val ss = SparkSession.builder
    .master("local[*]")
    .getOrCreate()

  val postLinksFileReader = new PostLinksFileReader()
  val postLinksDF = postLinksFileReader
    .readPostLinksFile("src/test/scala/resources/postLinksTest.xml")

  it should "read post links XML file and return data frame" in {
    postLinksDF.columns
    assert(postLinksDF.collect().length === 4)
  }

  it should "map post links XML attribute names (of row tag) to columns" in {
    val expectedColumns = Array("_CreationDate", "_Id", "_LinkTypeId", "_PostId", "_RelatedPostId")
    assert(postLinksDF.columns === expectedColumns)
  }

  it should "map post links XML attribute values (of row tag) to rows" in {
    val actualRow = postLinksDF.take(1)(0)

    assert(actualRow(0).toString === "2010-04-26 02:59:48.13")
    assert(actualRow(1) === 19)
    assert(actualRow(2) === 1)
    assert(actualRow(3) === 109)
    assert(actualRow(4) === 32412)
  }

  it should "be able to save as temp table and query data" in {
    postLinksDF.createOrReplaceTempView("post_links")
    val df = ss.sql("select * from post_links where _Id = 19")
    val row = df.collect
    assert(row.size === 1)
    assert(row(0)(3) === 109)
  }

}
