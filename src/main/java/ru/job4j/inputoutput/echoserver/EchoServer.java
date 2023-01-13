package ru.job4j.inputoutput.echoserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    /*curl -i http://localhost:9000/?msg=Hello
    Параметр -i указывает curl вывести всю информацию принятую от сервера.*/
    public static void main(String[] args) throws IOException {
        //создаем сервер
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                /*сервер ожидает обращения клиента*/
                Socket socket = server.accept();
                /*при обращении программа читает входной поток и отправляет ответ в выходной поток*/
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    /*записываем ответ*/
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.contains("Bye")) {
                            server.close();
                        }
                    }
                    /*окончательно отправляем ответ после чтения*/
                    out.flush();
                }
            }
        }
    }
}