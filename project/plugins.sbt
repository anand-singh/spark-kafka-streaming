resolvers ++= DefaultOptions.resolvers(snapshot = true)

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.3")

//Idea plugin
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.2")

// Setup sbteclipse
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "4.0.0")

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"




