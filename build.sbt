name := "btcBillionaire"
version := "0.1"

lazy val akkaHttpVersion = "10.2.7"
lazy val akkaVersion = "2.6.18"
lazy val akka = "2.5.31"
lazy val akkaHttp = "10.1.11"
lazy val jodaTime = "2.0"
lazy val json4s = "3.6.5"
lazy val mssqlJdbc = "6.1.0.jre8"

lazy val root = (project in file(".")).settings(
  inThisBuild(List(organization := "btcservice", scalaVersion := "2.12.7")),
  name := "akka-http-quickstart-scala",
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "org.json4s" %% "json4s-jackson" % json4s,
    "org.json4s" %% "json4s-native" % json4s,
    "joda-time" % "joda-time" % jodaTime,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
    "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
    "org.scalatest" %% "scalatest" % "3.1.4" % Test
  )
)
