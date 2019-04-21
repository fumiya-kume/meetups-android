include(":navigation")
include(":base")
include(":app")

val dataModuleList = listOf(
    ":config",
    ":api",
    ":entity",
    ":usersetting"
)
dataModuleList.forEach { moduleName ->
    include(":data${moduleName}")
}

val featureModuleList = listOf(
    ":overview",
    ":settingpage",
    ":search",
    ":walkthrough"
)
featureModuleList.forEach { moduleName ->
    include(":feature${moduleName}")
}