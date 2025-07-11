package triangles.exercise.service

import munit.FunSuite
import triangles.exercise.model.error.EmptyTriangleException

class TriangleFactoryTest extends FunSuite {
  test("fromRows should create Triangle for non-empty input") {
    val rows = Vector(Vector(1), Vector(2, 3))
    val result = TriangleFactory.fromRows(rows)
    assert(result.isRight)
    assert(result.exists(_.rows == rows))
  }

  test("fromRows should return error for empty input") {
    val result = TriangleFactory.fromRows(Vector.empty)
    assert(result.isLeft)
    assert(result.left.exists(_.getClass == classOf[EmptyTriangleException]))
    assert(result.left.exists(_.getMessage == "Triangle must have at least one row."))
  }
}
