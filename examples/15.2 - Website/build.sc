import mill._, scalalib._

object app extends ScalaModule{
  def scalaVersion = "2.13.1"
  def ivyDeps = Agg(
    ivy"io.getquill::quill-jdbc:3.5.0",
    ivy"org.postgresql:postgresql:42.2.8",
    ivy"com.opentable.components:otj-pg-embedded:0.13.1",
    ivy"com.lihaoyi::scalatags:0.8.4",
    ivy"com.lihaoyi::cask:0.5.6"
  )
  object test extends Tests{
    def testFrameworks = Seq("utest.runner.Framework")

    def ivyDeps = Agg(
      ivy"com.lihaoyi::utest::0.7.3",
      ivy"com.lihaoyi::requests::0.5.0"
    )
  }
}