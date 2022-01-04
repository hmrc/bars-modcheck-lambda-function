ThisBuild / name := "bars-modcheck-lambda-function"
ThisBuild / version := "1.0"
ThisBuild / scalaVersion := "2.12.14"

ThisBuild / assemblyJarName := "bars-modcheck-lambda-function_2.12-1.0.jar"

ThisBuild / parallelExecution := false

ThisBuild / libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-java-sdk-s3" % "1.11.150",
  "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",
  "commons-io" % "commons-io" % "2.5",
  "org.apache.httpcomponents" % "httpclient" % "4.5.3",
  "org.seleniumhq.selenium" % "htmlunit-driver" % "2.53.0",
  "org.seleniumhq.selenium" % "selenium-support" % "3.141.59",
  "org.mockito" % "mockito-core" % "2.8.47" % "test",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)
