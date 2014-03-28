resolvers += Resolver.url("plugins-artifactory", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

resolvers += "cavorite" at "http://files.cavorite.com/maven/"

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.8.5")

addSbtPlugin("com.cavorite" % "sbt-avro" % "0.2")

addSbtPlugin("com.twitter" %% "scrooge-sbt-plugin" % "3.12.3")