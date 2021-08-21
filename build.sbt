// This build is for this Giter8 template.
// To test the template run `g8` or `g8Test` from the sbt session.
// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details.
lazy val scalaVersions = Seq("2.12.14")
lazy val root = project
  .in(file("."))
  .disablePlugins(Giter8Plugin)
  .settings(name := "scala3-template")
  .aggregate(basic)

lazy val basic = project
  .in(file("basic"))
  .settings(
    name := "scala-cli-basic",
    Test / test := {
      val _ = (Test / g8Test).toTask("").value
    },
    scriptedLaunchOpts ++= List("-Xms1024m", "-Xmx1024m", "-XX:ReservedCodeCacheSize=128m", "-XX:MaxPermSize=256m", "-Xss2m", "-Dfile.encoding=UTF-8"),
    resolvers += Resolver.url("typesafe", url("https://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns),
    crossScalaVersions := scalaVersions
  )


ThisBuild / githubWorkflowJavaVersions := Seq("adopt@1.8", "adopt@1.11", "adopt@1.15")
ThisBuild / githubWorkflowScalaVersions := scalaVersions
ThisBuild / githubWorkflowPublishTargetBranches := Nil
Global / onChangedBuildSource := ReloadOnSourceChanges