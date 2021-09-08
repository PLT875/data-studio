package com.example
package writers

import org.apache.spark.sql.DataFrame

trait DbWriter {
  def write(df: DataFrame) : Unit
}
