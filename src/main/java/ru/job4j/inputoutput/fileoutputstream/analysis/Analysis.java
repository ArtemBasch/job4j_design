package ru.job4j.inputoutput.fileoutputstream.analysis;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean isOn = true;
        try (BufferedReader in = new BufferedReader(new FileReader(source)); PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line;
            while ((line = in.readLine()) != null) {
              String[] lines = line.split(" ");
              if ((lines[0].equals("400") || lines[0].equals("500")) && isOn) {
                  out.write(lines[1] + ";");
                  isOn = false;
              } else if ((lines[0].equals("200") || lines[0].equals("300")) && !isOn) {
                  out.write(lines[1] + ";" + System.lineSeparator());
                  isOn = true;//
              }
          }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis anal = new Analysis();
       anal.unavailable("./data/analysis/server.log", "./data/analysis/target.csv");
    }
}
