package triangles.exercise.service.pathfinder

import triangles.exercise.model.{Path, Triangle}

trait PathFinder {

  def findMinPath(triangle: Triangle): Path
}
