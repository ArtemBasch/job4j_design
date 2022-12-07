package ru.job4j.inputoutput.consolechat;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private boolean botOnLine = true;
    private boolean running = true;
    private boolean answerReady = false;
    private final List<String> log = new ArrayList<>();
    private List<String> botPhrases = new ArrayList<>();
    String human = " Вы: ";
    String bot = " Бот Ваня: ";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner =  new Scanner(System.in);
        String line;
        String tempLine;
            while (running) {
                line = scanner.nextLine();
                if (CONTINUE.equals(line) && !botOnLine) {
                    botOnLine = true;
                }
                if (STOP.equals(line)) {
                    botOnLine = false;
                }
                logger(human, line);
                if (botOnLine) {
                    tempLine = getRandomPhrase();
                    System.out.println(bot + tempLine);
                    logger(bot, tempLine);
                }
                if (OUT.equals(line)) {
                    saveLog(log);
                    running = false;
                }
            }
    }

    private void logger(String writer, String line) {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd MMM, HH:mm]").withLocale(Locale.US);
        String currentTime = time.format(formatter);
        StringBuffer sb = new StringBuffer(currentTime);
        sb.append(writer).append(line);
        log.add(sb.toString());
    }

    private String getRandomPhrase() {
        if (!answerReady) {
            botPhrases = readPhrases();
            answerReady = true;
        }
        int randIndex = (int) (Math.random() * botPhrases.size());
        String answer = botPhrases.get(randIndex);
        return  answer;
    }

    private List<String> readPhrases() {
        List<String> botPhrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(botAnswers), Charset.forName("UTF-8")))) {
            br.lines().forEach(botPhrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botPhrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(".\\src\\main\\java\\ru\\job4j\\inputoutput\\consolechat\\chatdata\\chatLog.txt",
                ".\\src\\main\\java\\ru\\job4j\\inputoutput\\consolechat\\chatdata\\botAnswers.txt");
        cc.run();
    }
}