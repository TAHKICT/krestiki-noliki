package RunGame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: админ
 * Date: 16.11.14
 * Time: 19:49
 * To change this template use File | Settings | File Templates.
 */
public class MakeGame {
    public static class MakeGameJSP{
        public static String makeGame() throws IOException {
            Random random = new Random();
            Path source = Paths.get("D:\\Dropbox\\KrestJava\\web\\game.jsp");
            String retAddr = "game"+random.nextInt(1000)+".jsp";
            String DestAdress = "D:\\Dropbox\\KrestJava\\out\\artifacts\\KrestJava_war_exploded\\"+retAddr;
            Path destination = Paths.get(DestAdress);
            Files.copy(source, destination);
            return retAddr;
        }

    }
}
