import sbt.*

object Dependencies {

  val Versions = new {
    val catsEffectVersion = "3.6.2"
    val mUnitVersion = "1.1.1"
    val mUnitCatsEffectVersion = "1.0.7"
  }

  val dependencies = Seq(
    "org.typelevel" %% "cats-effect" % Versions.catsEffectVersion,
    "org.scalameta" %% "munit" % Versions.mUnitVersion % Test,
    "org.typelevel" %% "munit-cats-effect-3" % Versions.mUnitCatsEffectVersion % Test
  )
}
