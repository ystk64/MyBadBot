package connection;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static connection.BotsBuildBots.*;

public class Connect {

    public void connection() throws IOException {

        Socket socket = new Socket("chat.freenode.net", 6667);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new Scanner(new InputStreamReader(socket.getInputStream()));

    }
}
