package triangles.exercise

import cats.Show
import cats.effect.std.Console
import cats.effect.{ExitCode, IO, IOApp}
import triangles.exercise.model.Path
import triangles.exercise.service.factory.TriangleFactory
import triangles.exercise.service.pathfinder.DummyPathFinder
import triangles.exercise.service.reader.ConsoleReader

object App extends IOApp.Simple:

  private val consoleReader   = new ConsoleReader(Console[IO])
  private val dummyPathFinder = new DummyPathFinder()

  override def run: IO[Unit] =
    for
      input    <- consoleReader.readInput
      triangle <- IO.fromEither(TriangleFactory.fromRows(input))
      minPath = dummyPathFinder.findMinPath(triangle)
      _ <- Console[IO].println(s"Minimal path is: ${Show[Path].show(minPath)}")
    yield ExitCode.Success
