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
import sbt._

object Dependencies {
  val resolutionRepos = Seq(
    ScalaToolsSnapshots,
    "Concurrent Maven Repo" at "http://conjars.org/repo" // For Scalding, Cascading etc
  )

  object V {
    val scalding  = "0.9.0rc16"
    val scalding_avro  = "0.9.0rc16"
    val scalding_commons  = "0.9.0rc16"
    val scalding_json  = "0.9.0rc16"
    val hadoop    = "0.20.2"
    val specs2    = "1.12.4.1" // -> "1.13" when we bump to Scala 2.10.0
    // Add versions for your additional libraries here...
  }

  object Libraries {
    val scaldingCore = "com.twitter"                %%  "scalding-core"       % V.scalding
    val scaldingAvro = "com.twitter"                %%  "scalding-avro"       % V.scalding_avro
    val scaldingCommons = "com.twitter"             %%  "scalding-commons"       % V.scalding_commons
    val scaldingJson = "com.twitter"             %%  "scalding-json"       % V.scalding_json
    val hadoopCore   = "org.apache.hadoop"          % "hadoop-core"           % V.hadoop       % "provided"
    // Add additional libraries from mvnrepository.com (SBT syntax) here...

    val slf4j_api = "org.slf4j" % "slf4j-api" % "1.7.5"
    val slf4j_simple = "org.slf4j" % "slf4j-simple" % "1.6.4"
    val slf4j_log4j12 = "org.slf4j" % "slf4j-log4j12" % "1.7.5"

    val scroogeCore = "com.twitter" % "scrooge-core_2.9.2" % "3.12.3"
    val thrift = "org.apache.thrift" % "libthrift" % "0.9.1"

    val dfsdatastores = "com.backtype" % "dfs-datastores" % "1.3.5"

    val chillThrift = "com.twitter" % "chill-thrift" % "0.3.6"

    // Scala (test only)
    val specs2       = "org.specs2"                 %% "specs2"               % V.specs2       % "test"
  }
}