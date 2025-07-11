import sbt.*

object Dependencies {

  val Versions = new {
    val catsEffectVersion = "3.6.2"
  }

  val dependencies = Seq(
    "org.typelevel" %% "cats-effect" % Versions.catsEffectVersion
  )
}
