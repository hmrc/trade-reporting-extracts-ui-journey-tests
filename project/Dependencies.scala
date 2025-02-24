import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "com.vladsch.flexmark" % "flexmark-all"   % "0.64.8" % Test,
    "org.scalatest"       %% "scalatest"      % "3.2.17" % Test,
    "uk.gov.hmrc"         %% "ui-test-runner" % "0.45.0" % Test,
    "org.pegdown"          % "pegdown"        % "1.6.0"  % Test,
    "com.typesafe"         % "config"         % "1.4.3"  % Test
  )

}
