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

  it should "read XML file and return data frame" in {
    val postLinksDF = postLinksFileReader
      .readPostLinksFile("src/test/scala/resources/postLinksTest.xml")

    val row0 = postLinksDF.take(1)

    assert(row0(0).length === 5)
    assert(postLinksDF.collect().length === 4)
  }

}
