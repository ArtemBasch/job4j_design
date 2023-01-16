package ru.job4j.inputoutput.echoserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.inputoutput.log4j.UsageLog4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    /**
     * curl -i http:/localhost:9000/?msg=Hello
     * Параметр -i указывает curl вывести всю информацию принятую от сервера.
     * создаем сервер
     * ServerSocket server = new ServerSocket(9000)
     *сервер ожидает обращения клиента
     *Socket socket = server.accept();
     * при обращении программа читает входной поток и отправляет ответ в выходной поток
     * try (OutputStream out = socket.getOutputStream();
     *                      BufferedReader in = new BufferedReader(
     *                              new InputStreamReader(socket.getInputStream())))
     *записываем ответ
     * out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
     * окончательно отправляем ответ после чтения
     * out.flush();
     */
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                        System.out.println(str);
                        if (str.contains("?msg=Bye") || str.contains("?msg=Exit")) {
                            server.close();
                        } else if (str.contains("?msg=Hello")) {
                            out.write("Hello, dear friend.".getBytes());
                        } else {
                            out.write("What".getBytes());
                        }
                    out.flush();
                }
            }
        } catch (Exception e) {
            LOG.error("IOException", e);
        }
    }
}