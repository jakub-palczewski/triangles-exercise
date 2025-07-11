package triangles.exercise.model

import cats.Show

case class Path(nodeValues: Vector[Int])

object Path:
  given Show[Path] = Show.show[Path] { path =>
    val sum = path.nodeValues.sum

    s"${path.nodeValues.mkString(" + ")} = $sum"
  }
