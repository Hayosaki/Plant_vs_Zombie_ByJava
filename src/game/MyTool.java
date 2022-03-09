package game;

import java.io.File;
import java.io.IOException;

public class MyTool {
    public static String toAbsolutePath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return file.getAbsolutePath();
    }
}
