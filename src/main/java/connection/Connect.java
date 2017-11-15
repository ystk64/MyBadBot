package connection;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import static connection.BotsBuildBots.in;
import static connection.BotsBuildBots.out;

class Connect {

    void createSocket() throws IOException {

        Socket socket = new Socket("chat.freenode.net", 6667);
        out = new PrintStream(socket.getOutputStream(), true);
        in = new Scanner(new InputStreamReader(socket.getInputStream()));

    }
}
