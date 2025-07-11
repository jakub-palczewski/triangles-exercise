package triangles.exercise.model

import cats.Show

case class Path(nodes: List[Node])

object Path:
  given Show[Path] = Show.show[Path] { path =>
    val values = path.nodes.map(_.value)
    val sum    = values.sum

    s"${values.mkString(" + ")} = $sum"
  }
