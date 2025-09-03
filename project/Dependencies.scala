import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "com.vladsch.flexmark" % "flexmark-all"   % "0.64.8" % Test,
    "org.scalatest"       %% "scalatest"      % "3.2.19" % Test,
    "uk.gov.hmrc"         %% "ui-test-runner" % "0.49.0" % Test,
    "com.typesafe"         % "config"         % "1.4.4"  % Test
  )

}
