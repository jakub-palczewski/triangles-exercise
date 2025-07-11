package triangles.exercise.service.pathfinder

import cats.effect.*
import fs2.*
import triangles.exercise.model.{Path, Triangle}

class StreamPathFinder extends PathFinder[IO]:

  private type PathInfo = (Int, Vector[Int])

  def findMinPath(triangle: Triangle): IO[Path] =
    val reversedRowStream = Stream.emits(triangle.rows.reverse)
    val initialPaths      = triangle.rows.last.map { value =>
      (value, Vector(value))
    }

    val finalPathStream = reversedRowStream.tail.scan(initialPaths) { (previousPaths, currentRow) =>
      currentRow.zipWithIndex.map { case (value, idx) =>
        selectOptimalChildPath(previousPaths, value, idx)
      }
    }

    extractFinalPathFromStream(finalPathStream, triangle)

  private def selectOptimalChildPath(
      previousPaths: Vector[PathInfo],
      currentValue: Int,
      currentIndex: Int
  ): PathInfo =
    val (leftSum, leftValues)   = previousPaths(currentIndex)
    val (rightSum, rightValues) = previousPaths(currentIndex + 1)

    if leftSum <= rightSum then (currentValue + leftSum, currentValue +: leftValues) // Prepend current value
    else (currentValue + rightSum, currentValue +: rightValues)

  private def extractFinalPathFromStream(
      pathStream: Stream[IO, Vector[PathInfo]],
      triangle: Triangle
  ): IO[Path] =
    pathStream.compile.last.map {
      case Some(finalPaths) =>
        val (_, pathValues) = finalPaths.head
        Path(pathValues)
      case None =>
        Path(triangle.rows.head)
    }
