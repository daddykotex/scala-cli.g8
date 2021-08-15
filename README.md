# Scala CLI giter8 template

This project contains a few different flavors of a Scala CLI application. They're built with Scala 3 and can generate standalone executable using Graal Native Image.

## Basic

[![asciicast](https://asciinema.org/a/oXqQzYiWuKeU4Znk6qKw5SKXO.svg)](https://asciinema.org/a/oXqQzYiWuKeU4Znk6qKw5SKXO)

### Usage

`sbt "new daddykotex/scala-cli.g8 -d basic"`

### Description

This project is a very basic CLI tool that does nothing but print its version.

Dependencies:

* org.typelevel:cats-effect_3:3.2.2
* com.monovore:decline-effect_3:2.1.0
* com.monovore:decline_3:2.1.0

sbt plugins:

* sbt-scalafmt
* sbt-scalafix
* sbt-native-image

