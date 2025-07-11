package triangles.exercise.service.pathfinder

import triangles.exercise.model.{Node, Path, Position, Triangle}


class DummyPathFinder extends PathFinder {
  def findMinPath(triangle: Triangle): Path = {
    val nodes = triangle.rows.indices.toVector.map { row =>
      Node(Position(row, 0), triangle.rows(row)(0))
    }

    val sum = nodes.map(_.value).sum
    Path(nodes)
  }
}
