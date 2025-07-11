package triangles.exercise.service.pathfinder

import triangles.exercise.model.{Path, Triangle}

trait PathFinder[F[_]] {

  def findMinPath(triangle: Triangle): F[Path]
}
