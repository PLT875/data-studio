name := "data-studio"

version := "0.1"

scalaVersion := "2.12.13"

idePackagePrefix := Some("com.example")

libraryDependencies += "org.apache.spark" %% "spark-core" % "3.1.2"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.1.2" % "provided"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test"
libraryDependencies += "com.databricks" %% "spark-xml" % "0.12.0"