package triangles.exercise.model

import cats.Show
import munit.FunSuite

class ShowPathTest extends FunSuite:
  test("Show[Path] should format path correctly") {
    val path = Path(
      Vector(
        Node(Position(0, 0), 1),
        Node(Position(1, 0), 2),
        Node(Position(2, 0), 3)
      )
    )
    val result = Show[Path].show(path)
    assertEquals(result, "1 + 2 + 3 = 6")
  }
