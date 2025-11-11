pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Modules"
include(":application")
include(":bookkeeping:bookkeeping-feature")
include(":bookkeeping:bookkeeping-core")
include(":acquiring:acquiring-feature")
include(":acquiring:acquiring-core")
include(":acquiringoffice:acquiringoffice-feature")
include(":acquiringoffice:acquiringoffice-core")
include(":currencyoperations:currencyoperations-feature")
include(":currencyoperations:currencyoperations-core")
include(":mainscreen:mainscreen-feature")
include(":mainscreen:mainscreen-core")
