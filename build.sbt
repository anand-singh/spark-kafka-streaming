name := """spark-kafka-streaming"""

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.5.1"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.5.1"
libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka" % "1.5.1"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

