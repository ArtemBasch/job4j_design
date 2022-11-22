package ru.job4j.inputoutput.scanfilesystem.dublicatesfinder;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashMap<FileProperty, Path> files = new HashMap<>();
    private List<Path> duplicates = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fProp = new FileProperty(file.toFile().length(), file.toFile().getName());

        if (!files.containsKey(fProp)) {
            files.put(fProp, file);
        } else {
            duplicates.add(file);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getDuplicates() {
        return duplicates;
    }
}