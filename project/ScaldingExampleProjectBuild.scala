/* 
 * Copyright (c) 2012 SnowPlow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */

import com.twitter.scrooge.ScroogeSBT
import sbt._
import Keys._

object ScaldingExampleProjectBuild extends Build {

  import Dependencies._
  import BuildSettings._

  // Configure prompt to show current project
  override lazy val settings = super.settings :+ {
    shellPrompt := { s => Project.extract(s).currentProject.id + " > " }
  }

  // Define our project, with basic project information and library dependencies
  lazy val project = Project("scalding-example-project", file("."))
    .settings(buildSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        Libraries.scaldingCore,
        Libraries.scaldingAvro,
        Libraries.scaldingCommons,
        Libraries.scaldingJson,
        Libraries.hadoopCore,
        Libraries.specs2,
        // Add your additional libraries here (comma-separated)...
//        Libraries.slf4j_api,
//        Libraries.slf4j_simple
        Libraries.slf4j_log4j12,
        Libraries.scroogeCore,
        Libraries.thrift,
        Libraries.dfsdatastores
      )
    )
    .settings(sbtavro.SbtAvro.avroSettings : _*)
    .settings(ScroogeSBT.newSettings : _*)

}
