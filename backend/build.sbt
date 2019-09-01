name := "designmatch"
organization := "com.grupo14.uniandes"
version := "1.0.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.0"

PlayKeys.devSettings := Seq("play.server.http.port" -> "9000")

libraryDependencies ++= Seq(
  jdbc,
  evolutions,
  guice,
  "org.jdbi" % "jdbi" % "2.78",
  "mysql" % "mysql-connector-java" % "5.1.46",
  "org.apache.commons" % "commons-lang3" % "3.0",
  "io.vavr" % "vavr" % "0.10.0",
  "javax.mail" % "mail" % "1.4",
  "io.vavr" % "vavr-jackson" % "0.9.0",
  "com.github.javafaker" % "javafaker" % "1.0.0" % "test"
)
