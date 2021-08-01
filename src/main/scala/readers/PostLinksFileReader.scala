package org.data.studio
package readers

import com.databricks.spark.xml.XmlDataFrameReader
import org.apache.spark.sql.{DataFrame, SparkSession}

class PostLinksFileReader(implicit ss: SparkSession) {

  def readPostLinksFile(path: String): DataFrame = {
    ss.read.format("xml")
      .option("rowTag", "row")
      .xml(path)
  }

}
