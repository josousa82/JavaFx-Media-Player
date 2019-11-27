package app;

import java.io.File;

public class test {
    public static void main(String[] args) {
        String workingDirectory = System.getProperty("user.dir");

        String fileName = "bunny.mp4";

        String absoluteFilePath = "";

        absoluteFilePath = workingDirectory + File.separator + fileName;

        File file = new File(absoluteFilePath);

        if(file.exists()) System.out.println("file exists = " + true);

        System.out.println("absoluteFilePath = " + absoluteFilePath);
        System.out.println("workingDirectory = " + workingDirectory);
    }
}
