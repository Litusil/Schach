name := "Schach"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.0.0" % "test")

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "2.0.0-M2"

libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.12" % "1.0.6"