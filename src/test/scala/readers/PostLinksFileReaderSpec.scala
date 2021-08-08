package org.data.studio
package readers

import org.apache.spark.sql.{Row, SparkSession}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class PostLinksFileReaderSpec extends AnyFlatSpec with Matchers {

  implicit val ss = SparkSession.builder
    .master("local[*]")
    .getOrCreate()

  val postLinksFileReader = new PostLinksFileReader()
  val postLinksDF = postLinksFileReader
    .readPostLinksFile("src/test/scala/resources/postLinksTest.xml")

  it should "read XML file and return data frame" in {
    postLinksDF.columns
    assert(postLinksDF.collect().length === 4)
  }

  it should "map XML attribute names (of row tag) to columns" in {
    val expectedColumns = Array("_CreationDate", "_Id", "_LinkTypeId", "_PostId", "_RelatedPostId")
    assert(postLinksDF.columns === expectedColumns)
  }

  it should "map XML attribute values (of row tag) to rows" in {
    val expectedRows = Array(
      Row("2010-04-26 02:59:48.13", 19, 1, 109, 32412)
    )
    
    assert(postLinksDF.collect() === expectedRows)
  }
}
