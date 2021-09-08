package com.example
package writers

import org.apache.spark.sql.{DataFrame, SaveMode}

import java.util.Properties

class UsersDbWriter extends DbWriter {

  def writeToTable(df: DataFrame): Unit = {
    // todo: externalize into properties file
    val dbProperties = new Properties()
    dbProperties.setProperty("user", "spark")
    dbProperties.setProperty("password", "123")
    dbProperties.put("driver", "org.postgresql.Driver")

    df.write
      .mode(SaveMode.Overwrite)
      .jdbc("jdbc:postgresql://postgresql:5432/stackoverflow", "users", dbProperties)
  }

  override def write(df: DataFrame): Unit = {
    val dbProperties = new Properties()
    dbProperties.setProperty("user", "spark")
    dbProperties.setProperty("password", "123")
    dbProperties.put("driver", "org.postgresql.Driver")

    df.write
      .mode(SaveMode.Overwrite)
      .jdbc("jdbc:postgresql://postgresql:5432/stackoverflow", "users", dbProperties)
  }

}
