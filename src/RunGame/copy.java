package RunGame;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: админ
 * Date: 16.11.14
 * Time: 18:25
 * To change this template use File | Settings | File Templates.
 */
public class copy {
    public static void main(String[] args) {
     Random random = new Random();
     Path source = Paths.get("web/game.jsp");
     Path destination = Paths.get("CurrentGames/game"+random.nextInt(1000)+".jsp");

        System.out.println(new File(".").getAbsolutePath());
               try{
            Path m = Files.copy(source, destination);
                   System.out.println(m.toString());
        }catch (IOException e){
            System.out.println(e);
        }

    }
}
