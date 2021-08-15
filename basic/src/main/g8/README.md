# Basic scala-cli project

This project is a Scala 3 simple CLI project. The CLI does nothing but print the version and the related help.

## Native image

The build allows you to build a standalone executable using the Graal `native-image` tool. To build the image run: `sbt scalaCli/nativeImage`.

The files under `src/main/resources/META-INF/native-image` are generated using the `native-image` JVM agent. If you add dependencies or encounter `ClassNotFoundException` or `NoSuchMethodException`, you should regenerate the `native-image` configuration. To do so, update the project to enable the JVM agent on run, and the run the CLI.

The update to the sbt project looks like:

```
diff --git a/basic/src/main/g8/build.sbt b/basic/src/main/g8/build.sbt
index 3374683..3c0f538 100644
--- a/basic/src/main/g8/build.sbt
+++ b/basic/src/main/g8/build.sbt
@@ -60,3 +60,4 @@ lazy val scalaCli = (project in file("scala-cli"))
     testFrameworks += new TestFramework("munit.Framework")
   )
   .settings(allNativeImageOptions)
+  .settings(runAgentSettings)
```

Once enabled, run the CLI and the agent will produce configuration files: `sbt "scalaCli/run --help"`