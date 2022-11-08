package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Server {

    static Logger logger = Logger.getLogger(Server.class.getName());

    static {
        try {
            FileHandler fh = new FileHandler("log.log");
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.FINE);
            logger.addHandler(fh);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.setLevel(Level.FINE);
    }
    public static void start() throws InterruptedException, IOException {
        try(ServerSocket server = new ServerSocket(4004);){
            while(true)
                serverStuff(server);
        }
    }

    public static void serverStuff(ServerSocket server) throws InterruptedException {
        try(Socket clientSocket = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                System.out.println("Підключення створенно!");
                boolean finished = false;
                while (!finished) {
                    int i = 0;
                    try {
                        out.flush();
                        String word = in.readLine();
                        System.out.println(word);
                        Command command = new Command(word);
                        System.out.println(command.commands);
                        Byte c = null;
                        for (i = 0; i < command.length && !finished; i++) {
                            c = command.commands.get(i);
                            switch (c) {
                                case 69:
                                case 87:
                                case 83:
                                case 68:
                                case 65:
                                case 32:
                                case 16:
                                case 37:
                                case 38:
                                case 39:
                                case 40: {
                                    for (int j = 0; j < 10; j++) {
                                        Pacman.Main.mf.control(c);
                                        Thread.sleep(100);
                                    }
                                    System.out.println(c + "c");
                                    out.write("Виконана команда ппереміщення " );
                                }
                                break;
                                case 1:
                                    out.write("К25 Ротань Ілля ");
                                    break;
                                case 0:
                                    out.write("Завершення роботи ");
                                    finished = true;
                                    break;
                                default:
                                    throw new IllegalArgumentException();
                            }
                        }
                        logger.log(Level.FINE, "command: " + c );
                    } catch (AssertionError e) {
                        out.write("Невірна кількість команд ");
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        out.write("Невірний запит з кліента ");
                    } catch (IllegalArgumentException e) {
                        out.write("Неіснуюча команда, було виконано " + i + " команд ");
                    }
                    out.newLine();
                    out.flush();
                }
            System.out.println("Підключення закрито!");
        }catch (IOException e){
            serverStuff(server);
        }
    }
}
