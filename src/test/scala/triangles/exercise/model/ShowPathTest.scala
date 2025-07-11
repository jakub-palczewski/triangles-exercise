package triangles.exercise.model

import cats.Show
import munit.FunSuite

class ShowPathTest extends FunSuite:
  test("Show[Path] should format path correctly") {
    val path = Path(
      Vector(1, 2, 3)
    )
    val result = Show[Path].show(path)
    assertEquals(result, "1 + 2 + 3 = 6")
  }
