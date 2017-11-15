package connection;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import static connection.BotsBuildBots.in;
import static connection.BotsBuildBots.out;

class Connect {

    void createSocket(String host, int port) throws IOException {

        Socket socket = new Socket(host, port);
        out = new PrintStream(socket.getOutputStream(), true);
        in = new Scanner(new InputStreamReader(socket.getInputStream()));

    }
}
