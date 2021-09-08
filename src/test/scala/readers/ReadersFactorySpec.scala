package com.example
package readers

import org.apache.spark.sql.SparkSession
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

import java.util.TimeZone

class ReadersFactorySpec extends AnyFlatSpec with Matchers {

  TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
  implicit val ss = SparkSession.builder
    .master("local[*]")
    .getOrCreate()

  it should "return post links reader" in {
    val reader = ReadersFactory.getReader("postLinks")
    assert(reader.isInstanceOf[PostLinksFileReader])
  }

  it should "return users reader" in {
    val reader = ReadersFactory.getReader("users")
    assert(reader.isInstanceOf[UsersFileReader])
  }

  it should "return exception if unknown reader supplied" in {
    assertThrows[IllegalArgumentException] {
      ReadersFactory.getReader("random")
    }
  }

}
