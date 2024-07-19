val scala3Version = "3.4.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "recipe-streaming",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "1.0.0" % Test,
	    "org.apache.kafka" % "kafka-clients" % "3.7.1",
	    "com.softwaremill.sttp.client4" %% "core" % "4.0.0-M6",
      "com.lihaoyi" %% "upickle" % "3.1.0"
    )
  )
