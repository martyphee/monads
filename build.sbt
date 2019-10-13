name := "monads"

version := "0.1"

ThisBuild / scalaVersion := "2.13.1"

//ThisBuild / watchTriggeredMessage := Watched.clearWhenTriggered

ThisBuild / autoStartServer := false

ThisBuild / scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:postfixOps"
)

ThisBuild / shellPrompt := (_ => fancyPrompt(name.value))

def fancyPrompt(projectName: String): String =
  s"""|
      |[info] Welcome to the ${cyan(projectName)} project!
      |sbt>""".stripMargin

def cyan(str: String): String =
  scala.Console.CYAN + str + scala.Console.RESET

lazy val `fp-library` =
  project
    .in(file("./1-fp-library"))
    .settings(shellPrompt := (_ => fancyPrompt(name.value)))

lazy val `application-library` =
  project
    .in(file("./2-application-library"))
    .settings(shellPrompt := (_ => fancyPrompt(name.value)))
    .dependsOn(`fp-library`)

lazy val `end-of-the-world` =
  project
    .in(file("./3-end-of-the-world"))
    .settings(shellPrompt := (_ => fancyPrompt(name.value)))
    .dependsOn(`application-library`)

addCommandAlias("ll", "projects")
addCommandAlias("cd", "project")
addCommandAlias("root", "project monads")
addCommandAlias("lib", "project fp-library")
addCommandAlias("app", "project appliction-library")
addCommandAlias("main", "project end-of-the-world")

