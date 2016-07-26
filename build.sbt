import sbtassembly.AssemblyKeys
import sbtassembly.AssemblyPlugin.autoImport._

name := "spark_ignite"

version := "1.0"

val spark = "org.apache.spark" %% "spark-core" % "1.6.0" % "provided" exclude("org.apache.spark", "commons")
val ignite = "org.apache.ignite" % "ignite-core" % "1.6.0"
val ignite_spark = "org.apache.ignite" % "ignite-spark" % "1.6.0"

lazy val commonSettings = Seq(
  organization := "com.knoldus",
  version := "0.1.0",
  scalaVersion := "2.11.0"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "spark-ignite",
    libraryDependencies ++= Seq(spark, ignite, ignite_spark),
    mainClass in assembly := Some("com.knoldus.SparkIgniteAppOne")
  )
mergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$") => MergeStrategy.discard
  case "reference.conf" => MergeStrategy.concat
  case _ => MergeStrategy.first
}
assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("org.apache.commons.io.**" -> "shadeio.@1").inAll,
  ShadeRule.rename("com.esotericsoftware.kryo.**" -> "shadekio.@1").inAll
)