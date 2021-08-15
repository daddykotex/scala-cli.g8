import cats.effect.*
import cats.implicits.*

import com.monovore.decline._
import com.monovore.decline.effect._

object Main
    extends CommandIOApp(
      name = "scala-cli",
      header = "Simple CLI tool",
      version = "0.0.x"
    ) {
  override def main: Opts[IO[ExitCode]] =
    Opts.never
}
