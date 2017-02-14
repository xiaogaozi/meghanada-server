java_import(
    name = "tools",
    jars = ["@bazel_tools//tools/jdk:langtools"],
)

java_binary(
    name = "meghanada-server",
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/*"]),
    main_class = "meghanada.Main",
    deps = [":tools",
            "//external:maven-model-jar",
            "//external:plexus-utils-jar",
            "//external:motif-jar",
            "//external:motif-hamcrest-jar",
            "//external:javaparser-core-jar",
            "//external:log4j-core-jar",
            "//external:log4j-api-jar",
            "//external:log4j-slf4j-impl-jar",
            "//external:commons-lang3-jar",
            "//external:commons-cli-jar",
            "//external:gradle-tooling-api-jar",
            "//external:guava-jar",
            "//external:asm-jar",
            "//external:kryo-jar",
            "//external:config-jar",
            "//external:evo-inflector-jar",
            "//external:junit-jar",
            "//external:roaster-api-jar",
            "//external:roaster-jdt-jar",
            "//external:zstd-jni-jar",
            "//external:android-builder-model-jar",],

    runtime_deps = ["//external:objenesis-jar",
                    "//external:hamcrest-core-jar",
                    "//external:reflectasm-jar",
                    "//external:minlog-jar",
                    "//external:slf4j-api-jar",
                    "//external:android-gradle-api-jar",
                    ]
)