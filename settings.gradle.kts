rootProject.name = "demo"

include("common")
project(":common").projectDir = file("components/common")

include("todo")
project(":todo").projectDir = file("applications/todo")

include("algorithm")
project(":algorithm").projectDir = file("applications/algorithm")