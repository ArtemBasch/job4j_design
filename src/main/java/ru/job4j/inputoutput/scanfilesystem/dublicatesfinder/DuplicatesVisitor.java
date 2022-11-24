package ru.job4j.inputoutput.scanfilesystem.dublicatesfinder;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashMap<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fProp = new FileProperty(file.toFile().length(), file.toFile().getName());
        files.putIfAbsent(fProp, new ArrayList<>());
        files.get(fProp).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void findDuplicates() {
        for (Map.Entry<FileProperty, List<Path>> entry : files.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.printf("Duplicates found: %s %d \n", entry.getKey().getName(), entry.getKey().getSize());
                for (Path path : entry.getValue()) {
                    System.out.println(path);
                }
            }
        }
    }
}