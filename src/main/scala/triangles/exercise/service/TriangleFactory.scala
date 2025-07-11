package triangles.exercise.service

import triangles.exercise.model.Triangle
import triangles.exercise.model.error.EmptyTriangleException

object TriangleFactory:

  def fromRows(rows: Vector[Vector[Int]]): Either[EmptyTriangleException, Triangle] =
    Either.cond(
      rows.nonEmpty,
      Triangle(rows),
      new EmptyTriangleException
    )
