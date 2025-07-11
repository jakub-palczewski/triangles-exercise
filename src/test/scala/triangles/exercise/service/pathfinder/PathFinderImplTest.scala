package triangles.exercise.service.pathfinder

import triangles.exercise.model.{Path, Triangle}
import munit.FunSuite

class PathFinderImplTest extends FunSuite {

  val pathFinder = new PathFinderImpl()

  test("findMinPath should handle single element triangle") {
    val triangle = Triangle(Vector(Vector(5)))
    val result = pathFinder.findMinPath(triangle)
    val expected = Path(Vector(5))
    assertEquals(result, expected)
  }

  test("findMinPath should handle two-row triangle - left path optimal") {
    val triangle = Triangle(Vector(
      Vector(3),
      Vector(1, 4)
    ))
    val result = pathFinder.findMinPath(triangle)
    val expected = Path(Vector(3, 1))
    assertEquals(result, expected)
  }

  test("findMinPath should handle two-row triangle - right path optimal") {
    val triangle = Triangle(Vector(
      Vector(3),
      Vector(7, 2)
    ))
    val result = pathFinder.findMinPath(triangle)
    val expected = Path(Vector(3, 2))
    assertEquals(result, expected)
  }

  test("findMinPath should handle three-row triangle") {
    val triangle = Triangle(Vector(
      Vector(1),
      Vector(2, 3),
      Vector(4, 5, 6)
    ))
    val result = pathFinder.findMinPath(triangle)
    // Expected path: 1 -> 2 -> 4 (sum = 7)
    val expected = Path(Vector(1, 2, 4))
    assertEquals(result, expected)
  }

  test("findMinPath should handle triangle with negative numbers") {
    val triangle = Triangle(Vector(
      Vector(1),
      Vector(-2, 3),
      Vector(4, -5, 6)
    ))
    val result = pathFinder.findMinPath(triangle)
    // Expected path: 1 -> -2 -> -5 (sum = -6)
    val expected = Path(Vector(1, -2, -5))
    assertEquals(result, expected)
  }

  test("findMinPath should handle triangle where right path is better") {
    val triangle = Triangle(Vector(
      Vector(1),
      Vector(10, 2),
      Vector(5, 3, 1)
    ))
    val result = pathFinder.findMinPath(triangle)
    // Expected path: 1 -> 2 -> 1 (sum = 4)
    val expected = Path(Vector(1, 2, 1))
    assertEquals(result, expected)
  }

  test("findMinPath should handle larger triangle") {
    val triangle = Triangle(Vector(
      Vector(7),
      Vector(6, 3),
      Vector(3, 8, 5),
      Vector(11, 2, 10, 9)
    ))
    val result = pathFinder.findMinPath(triangle)
    // Expected path: 7 -> 6 -> 3 -> 2 (sum = 20)
    val expected = Path(Vector(7, 6, 3, 2))
    assertEquals(result, expected)
  }

  test("findMinPath should handle triangle with all same values") {
    val triangle = Triangle(Vector(
      Vector(5),
      Vector(5, 5),
      Vector(5, 5, 5)
    ))
    val result = pathFinder.findMinPath(triangle)
    // Any path is valid, but should be consistent (left-most when equal)
    val expected = Path(Vector(5, 5, 5))
    assertEquals(result, expected)
  }

  test("findMinPath should handle triangle with zeros") {
    val triangle = Triangle(Vector(
      Vector(0),
      Vector(1, 0),
      Vector(2, 0, 1)
    ))
    val result = pathFinder.findMinPath(triangle)
    // Expected path: 0 -> 0 -> 0 (sum = 0)
    val expected = Path(Vector(0, 0, 0))
    assertEquals(result, expected)
  }

  test("findMinPath should handle triangle of 500 rows") {
    val rows = (1 to  500).map { row =>
      (1 to row).toVector
    }.toVector
    val triangle = Triangle(rows)
    val result = pathFinder.findMinPath(triangle)
    // Expected path: 1 -> 0 -> 0 ... (sum = 500)
    val expected = Path(Vector.fill(500)(1))
    assertEquals(result, expected)
  }

  test("findMinPath should handle triangle of 600 rows") {
    val rows = (1 to 600).map { row =>
      (1 to row).toVector
    }.toVector
    val triangle = Triangle(rows)
    val result = pathFinder.findMinPath(triangle)
    // Expected path: 1 -> 0 -> 0 ... (sum = 600)
    val expected = Path(Vector.fill(600)(1))
    assertEquals(result, expected)
  }

  test("findMinPath should handle triangle of 1000 rows") {
    val rows = (1 to 1000).map { row =>
      (1 to row).toVector
    }.toVector
    val triangle = Triangle(rows)
    val result = pathFinder.findMinPath(triangle)
    // Expected path: 1 -> 0 -> 0 ... (sum = 1000)
    val expected = Path(Vector.fill(1000)(1))
    assertEquals(result, expected)
  }
}
