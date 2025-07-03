package Ch12Ex5;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Create an empty File3.txt if it doesn't exist
        try {
            new FileOutputStream("/home/alibabji/IdeaProjects/ICS4U/src/Ch12Ex5/File3.txt").close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Instantiate MergeFiles object and run the merging process
        MergeFiles mergeFiles = new MergeFiles();
        mergeFiles.run();
        System.out.println("Done!");
    }
}
