package $package$

import cats.effect.*
import cats.implicits.*

import com.monovore.decline._
import com.monovore.decline.effect._

object Main
    extends CommandIOApp(
      name = "$name$",
      header = "Simple CLI tool",
      version = BuildInfo.version
    ) {
  override def main: Opts[IO[ExitCode]] =
    Opts.never
}
