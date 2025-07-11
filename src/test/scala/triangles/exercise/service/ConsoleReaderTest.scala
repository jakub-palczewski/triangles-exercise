package triangles.exercise.service

import cats.effect.std.Console
import cats.effect.{IO, Ref}
import munit.CatsEffectSuite

import java.nio.charset.Charset

class ConsoleReaderTest extends CatsEffectSuite:

  // Helper to create test console
  def makeTestConsole(inputLines: List[String]): IO[Console[IO]] =
    Ref.of[IO, List[String]](inputLines).map { linesRef =>
      new Console[IO]:
        override def readLine: IO[String] =
          linesRef.modify {
            case head :: tail => (tail, head)
            case Nil          => (Nil, "")
          }

        override def readLineWithCharset(charset: Charset): IO[String] = readLine

        def print[A](a: A)(implicit S: cats.Show[A]): IO[Unit]   = IO.unit
        def println[A](a: A)(implicit S: cats.Show[A]): IO[Unit] = IO.unit
        def error[A](a: A)(implicit S: cats.Show[A]): IO[Unit]   = IO.unit
        def errorln[A](a: A)(implicit S: cats.Show[A]): IO[Unit] = IO.unit
    }

  test("ConsoleReader should read valid triangle") {
    val input = List(
      "7",
      "6 3",
      "3 8 5",
      "11 2 10 9",
      "" // empty line to finish
    )

    val expected = Vector(
      Vector(7),
      Vector(6, 3),
      Vector(3, 8, 5),
      Vector(11, 2, 10, 9)
    )

    for
      testConsole <- makeTestConsole(input)
      reader = new ConsoleReader(testConsole)
      result <- reader.readInput
    yield assertEquals(result, expected)
  }

  test("ConsoleReader should handle empty input") {
    val input = List("") // immediately empty

    for
      testConsole <- makeTestConsole(input)
      reader = new ConsoleReader(testConsole)
      result <- reader.readInput
    yield assertEquals(result, Vector.empty)
  }

  test("ConsoleReader should fail on invalid row size") {
    val input = List(
      "7",
      "6 3 4", // Wrong size - should have 2 elements, not 3
      ""
    )

    for
      testConsole <- makeTestConsole(input)
      reader = new ConsoleReader(testConsole)
      result <- reader.readInput.attempt
    yield
      assert(result.isLeft)
      result.left.foreach { error =>
        assert(error.isInstanceOf[IllegalArgumentException])
        assert(error.getMessage.contains("Row size mismatch: expected 2, got 3"))
      }
  }

  test("ConsoleReader should fail on non-integer input") {
    val input = List(
      "7",
      "6 abc", // Not a valid integer
      ""
    )

    for
      testConsole <- makeTestConsole(input)
      reader = new ConsoleReader(testConsole)
      result <- reader.readInput.attempt
    yield
      assert(result.isLeft)
      result.left.foreach { error =>
        assert(error.isInstanceOf[IllegalArgumentException])
        assert(error.getMessage.contains("[abc] is not a valid integer"))
      }
  }

  test("ConsoleReader should handle multiple spaces between numbers") {
    val input = List(
      "7",
      "6   3",      // Multiple spaces
      " 3  8   5 ", // Leading/trailing spaces
      "11 2 10 9",
      ""
    )

    val expected = Vector(
      Vector(7),
      Vector(6, 3),
      Vector(3, 8, 5),
      Vector(11, 2, 10, 9)
    )

    for
      testConsole <- makeTestConsole(input)
      reader = new ConsoleReader(testConsole)
      result <- reader.readInput
    yield assertEquals(result, expected)
  }

  test("ConsoleReader should validate triangle structure incrementally") {
    val input = List(
      "7",
      "6 3",
      "3 8", // Wrong - should have 3 elements
      ""
    )

    for
      testConsole <- makeTestConsole(input)
      reader = new ConsoleReader(testConsole)
      result <- reader.readInput.attempt
    yield
      assert(result.isLeft)
      result.left.foreach { error =>
        assert(error.getMessage.contains("Row size mismatch: expected 3, got 2"))
      }
  }
