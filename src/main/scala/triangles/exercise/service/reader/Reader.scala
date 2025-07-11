package triangles.exercise.service.reader

trait Reader[F[_]]:

  def readInput: F[Vector[Vector[Int]]]

