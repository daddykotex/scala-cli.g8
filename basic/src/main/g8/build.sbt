lazy val theScalaVersion = "3.0.1"
lazy val root = (project in file("."))
  .settings(
    inThisBuild(
      List(
        organization := "$package$",
        scalaVersion := theScalaVersion,
        githubWorkflowJavaVersions := Seq("adopt@1.8", "adopt@1.11", "adopt@1.15"),
        githubWorkflowScalaVersions := Seq(theScalaVersion),
        githubWorkflowPublishTargetBranches := Seq.empty
      )
    ),
    name := "scala-cli"
  )
  .aggregate(scalaCli)


lazy val declineVersion = "2.1.0"
lazy val catsVersion = "3.2.2"

lazy val debugOptionsNativeImage = Seq(
  "-H:+ReportExceptionStackTraces",
  "-H:+ReportUnsupportedElementsAtRuntime",
  "-H:+TraceClassInitialization",
  "-H:+PrintClassInitialization",
  "-H:+StackTrace",
  "-H:+JNI",
  "-H:-SpawnIsolates",
  "-H:-UseServiceLoaderFeature",
  "-H:+RemoveSaturatedTypeFlows"
)

lazy val runAgentSettings = Def.setting(
  run / javaOptions := {
    val path = baseDirectory.value / "src" / "main" / "resources" / "META-INF" / "native-image"
    Seq(s"-agentlib:native-image-agent=config-output-dir=\$path")
  }
)

lazy val allNativeImageOptions = Def.settings(
  nativeImageOptions ++= List(
    "--verbose",
    "--no-server",
    "--no-fallback",
    "--enable-http",
    "--enable-https",
    "--enable-all-security-services",
    "--report-unsupported-elements-at-runtime",
    "--allow-incomplete-classpath",
    "--initialize-at-build-time=scala,org.slf4j.LoggerFactory"
  )
)

lazy val scalaCli = (project in file("scala-cli"))
  .enablePlugins(NativeImagePlugin)
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name := "scala-cli",
    Compile / mainClass := Some("$package$.Main"),
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % catsVersion,
      "com.monovore" %% "decline" % declineVersion,
      "com.monovore" %% "decline-effect" % declineVersion,
      "org.scalameta" %% "munit" % "0.7.28" % Test,
      "org.typelevel" %% "munit-cats-effect-3" % "1.0.5" % Test
    ),
    run / fork := true,
    testFrameworks += new TestFramework("munit.Framework"),
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := organization.value
  )
  .settings(allNativeImageOptions)
