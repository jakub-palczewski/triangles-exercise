package triangles.exercise

import cats.effect.std.Console
import cats.effect.{ExitCode, IO, IOApp}
import triangles.exercise.service.ConsoleReader

object App extends IOApp.Simple:

  private val consoleReader = new ConsoleReader(Console[IO])

  override def run: IO[Unit] =
    for
      input <- consoleReader.readInput
      _     <- Console[IO].print(input)
    yield ExitCode.Success
