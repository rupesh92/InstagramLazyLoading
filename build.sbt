name := """InstragramLazyLoading"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs
)

libraryDependencies ++= Seq(
  "com.sachinhandiekar" % "jInstagram" % "1.0.10"
)

libraryDependencies ++= Seq(
  "org.json" % "org.json" % "chargebee-1.0"
)