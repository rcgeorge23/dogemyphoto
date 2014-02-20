grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.project.dependency.resolution = {
    inherits("global")
    plugins {
        build(":release:2.2.1") {
            export = false
        }
    }
}
