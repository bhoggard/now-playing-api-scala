name := """now-playing"""
organization := "com.tristanmedia"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  guice,
  "com.softwaremill.sttp"   %% "core"                             % "1.3.0",
  "com.softwaremill.sttp"   %% "async-http-client-backend-future" % "1.3.0",
  "com.newrelic.agent.java" % "newrelic-api"                      % "4.4.0", // Monitoring
  "org.scalatestplus.play"  %% "scalatestplus-play"               % "3.1.2" % Test
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.tristanmedia.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.tristanmedia.binders._"
