includeTargets << grailsScript("_GrailsClean")

target(main: "fires off grails clean and also deletes the target and project work directory") {
    depends(cleanAll)
    ant.delete(dir:"$projectWorkDir")
    ant.delete(dir:"$basedir/target")
}

setDefaultTarget(main)
