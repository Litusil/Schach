name := "Schach"

version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.0.0" % "test")

scalacOptions in Test ++= Seq("-Yrangepos")