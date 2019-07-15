ThisBuild / name := "scalameta-demo"
ThisBuild / organization := "com.example"
ThisBuild / version      := "0.1.0-SNAPSHOT"

lazy val commonSettings = Seq(
  scalaVersion := "2.13.0",
)

lazy val beforeTransform = (project in file("before-transform"))
  .settings(
    commonSettings,
  )

lazy val afterTransform = (project in file("after-transform"))
  .settings(
    commonSettings,
  )

lazy val root = (project in file("."))
  .dependsOn(afterTransform)
  .settings(
    commonSettings,
    sourceGenerators in Compile += Def.task {
      Generator.gen(
        inputDir = baseDirectory.value / "before-transform" / "src" / "main" / "scala",
        outputDir = baseDirectory.value / "after-transform" / "src" / "main" / "scala"
      )
    }.taskValue,
  )










