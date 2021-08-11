package com.example
package readers

import org.apache.spark.sql.SparkSession
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

import java.util.TimeZone

class UsersFileReaderSpec extends AnyFlatSpec with Matchers {
  TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
  implicit val ss = SparkSession.builder
    .master("local[*]")
    .getOrCreate()

  val usersFileReader = new UsersFileReader()
  val usersDF = usersFileReader
    .readUsersFile("src/test/scala/resources/usersTest.xml")

  it should "read users XML file and return data frame" in {
    usersDF.columns
    assert(usersDF.collect().length === 3)
  }

  it should "map users XML attribute names (of row tag) to columns" in {
    val expectedColumns = Array("_AboutMe", "_CreationDate", "_DisplayName", "_DownVotes", "_Id", "_LastAccessDate", "_Reputation", "_UpVotes", "_Views")
    assert(usersDF.columns === expectedColumns)
  }

  it should "map users XML attribute values (of row tag) to rows" in {
    val actualRow = usersDF.take(1)(0)

    assert(actualRow(0).toString === "A third fake community for demonstration purposes - if you see this, report it on Meta please :)")
    assert(actualRow(1).toString === "2021-05-28 13:16:43.943")
    assert(actualRow(2) === "AudioBubble3")
    assert(actualRow(3) === 0)
    assert(actualRow(4) === -1003)
    assert(actualRow(5).toString === "2021-05-28 13:16:43.943")
    assert(actualRow(6) === 1)
    assert(actualRow(7) === 0)
    assert(actualRow(8) === 0)
  }

}
