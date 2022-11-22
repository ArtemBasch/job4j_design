package ru.job4j.inputoutput.scanfilesystem.dublicatesfinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:\\projects"), duplicatesVisitor);
       for (Path paths : duplicatesVisitor.getDuplicates()) {
           System.out.printf("Name %s size %d Kb \n Path %s \n", paths.toFile().getName(), paths.toFile().length() / 1024, paths.toFile().getAbsolutePath());
       }
    }
}