package triangles.exercise.service

trait Reader[F[_]]:

  def readInput: F[Vector[Vector[Int]]]

