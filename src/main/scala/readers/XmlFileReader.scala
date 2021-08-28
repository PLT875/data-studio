package com.example
package readers

import org.apache.spark.sql.DataFrame

trait XmlFileReader {

  def read(path: String): DataFrame

}
