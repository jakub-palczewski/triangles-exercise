package triangles.exercise.service

import cats.effect.IO
import cats.effect.std.Console
import triangles.exercise.service.ConsoleReader.{Separator, UserMessage}

import scala.util.Try

class ConsoleReader(console: Console[IO]) extends Reader[IO]:
  override def readInput: IO[Vector[Vector[Int]]] =
    def readLoop(acc: Vector[Vector[Int]]): IO[Vector[Vector[Int]]] =
      console.readLine.flatMap { line =>
        val trimmedLine = line.trim
        if isLineEmpty(trimmedLine) then IO.pure(acc)
        else
          for
            parsedLine   <- parseLine(trimmedLine)
            validatedRow <- acc.lastOption.map(_.size).fold(IO.pure(parsedLine)) { lastRowSize =>
              validateRowSize(lastRowSize + 1, parsedLine)
            }
            next <- readLoop(acc :+ validatedRow)
          yield next
      }

    console.println(UserMessage) *> readLoop(
      Vector.empty[Vector[Int]]
    )

  private def isLineEmpty(line: String): Boolean = line.isBlank

  private def validateRowSize(expectedLength: Int, row: Vector[Int]): IO[Vector[Int]] =
    if row.length != expectedLength then
      IO.raiseError(
        new IllegalArgumentException(s"Row size mismatch: expected $expectedLength, got ${row.length}")
      ) // TODO: consider using a custom error type
    else IO.pure(row)

  private def parseLine(line: String): IO[Vector[Int]] =
    val splitElements = line.trim.split("\\s+").toVector

    IO.traverse(splitElements) { element =>
      IO.fromTry(Try(element.toInt))
        .adaptError { case _: NumberFormatException =>
          new IllegalArgumentException(
            s"Invalid input. [$element] is not a valid integer."
          ) // TODO: consider using a custom error type
        }
    }

object ConsoleReader:
  val Separator: String   = " "
  val UserMessage: String = "Please enter a line of space separated integers. Press ENTER to finish"
