dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Dagger"
include(":api")
project(":api").projectDir = File("05_modularization/api")

include(":app")
project(":app").projectDir = File("05_modularization/app")

include(":core")
project(":core").projectDir = File("05_modularization/core")

include(":news_list")
project(":news_list").projectDir = File("05_modularization/news_list")
