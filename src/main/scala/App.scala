import cats.effect.std.Console
import cats.effect.{ExitCode, IO, IOApp}

object App extends IOApp.Simple {

  override def run: IO[Unit] =
    for {
      _ <- Console[IO].println("Hello, World!")
    } yield ExitCode.Success
}
