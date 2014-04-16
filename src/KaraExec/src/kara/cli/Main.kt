package kara.cli

import java.util.HashMap
import kara.*
import kara.internal.*
import kara.server.JettyRunner
import org.apache.log4j.*
import java.util.ArrayList
import java.net.URL
import java.io.File

fun server(appConfig : ApplicationConfig) {
    val jettyRunner = JettyRunner(appConfig)
    jettyRunner.start()
}

fun config(appConfig: ApplicationConfig) {
    println(appConfig.toString())
}

fun main(args: Array<String>) {
    val appConfig = ApplicationConfig(if (args.size > 0) args[0] else "development.conf")

    val logPath = appConfig.tryGet("kara.logPropertiesPath")

    if (logPath != null) {
        PropertyConfigurator.configureAndWatch(logPath, 5000)
    }
    else {
        BasicConfigurator.configure()
        LogManager.getRootLogger()?.setLevel(Level.INFO)
    }

    config(appConfig)
    server(appConfig)
}
