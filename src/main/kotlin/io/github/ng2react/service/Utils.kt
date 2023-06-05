package io.github.ng2react.service

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.name
import kotlin.io.path.pathString


fun isAngularLike(file: Path): Boolean {
    val filename = file.name
    if (filename.endsWith(".ts") && !filename.endsWith(".d.ts")) {
        return true
    }
    if (filename.endsWith(".js")) {
        return true
    }
    return false;
}

fun findAllAngularFiles(dir: Path): List<File> {
    val files = mutableListOf<File>()
    Files.walk(dir).forEach {
        if (Files.isRegularFile(it) && isAngularLike(it)) {
            files.add(it.toFile())
        }
    }
    return files
}