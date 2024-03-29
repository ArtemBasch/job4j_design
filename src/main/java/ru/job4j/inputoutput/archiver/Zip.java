package ru.job4j.inputoutput.archiver;

import ru.job4j.inputoutput.argsname.ArgsName;
import ru.job4j.inputoutput.scanfilesystem.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validate(ArgsName argsName) {
        File file = new File(argsName.get("d"));
        if (argsName.get("d").equals(null)) {
            throw new IllegalArgumentException("Root folder is null.");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file));
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException(String.format("Not an extension %s", argsName.get("e")));
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Wrong input data format");

        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        zip.validate(argsName);
        Path source = Paths.get(argsName.get("d"));
        File target = new File(argsName.get("o"));
        zip.packFiles(Search.search(source, pred -> !pred.toFile().getName().endsWith(argsName.get("e"))), target);

    }
}