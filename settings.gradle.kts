pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ru.mirea.Kirillova.Lesson6"
include(":app")
include(":securesharedpreferences")
include(":internalfilestorage")
include(":notebook")
include(":employeedb")
