ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.7.1"

ThisBuild / javacOptions ++= Seq("-source", "11", "-target", "11")

 scalacOptions ++= Seq(
   "-encoding", "utf8",
   "-feature",
   "-deprecation",
   "-unchecked",
   "-Xfatal-warnings",
   "-Xkind-projector",
   "-Wnonunit-statement"
 )



lazy val root = (project in file("."))
  .settings(
    name := "triangles-exercise",
    libraryDependencies := Dependencies.dependencies,
    Compile / run / fork := true
  )
