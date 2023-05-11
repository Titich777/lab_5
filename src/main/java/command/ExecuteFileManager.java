package command;

import java.io.*;
import java.util.LinkedList;

public class ExecuteFileManager implements UserInput {
    private static final LinkedList<String> pathList = new LinkedList<>();
    private static final LinkedList<BufferedReader> fileReaders = new LinkedList<>();

    public static void pushFile(String path) throws FileNotFoundException {
        pathList.push(new File(path).getAbsolutePath());
        fileReaders.push(new BufferedReader(new InputStreamReader(new FileInputStream(path))));
    }

    public static File getFile() {
        return new File(pathList.getLast());
    }

    public static String readLine() throws IOException {
        return fileReaders.getFirst().readLine();
    }

    public static void popFile() throws IOException {
        fileReaders.getFirst().close();
        fileReaders.pop();
        pathList.pop();
    }

    public static boolean fileRepeat(String path) {
        return pathList.contains(new File(path).getAbsolutePath());
    }

    @Override
    public String nextLine() {
        try {
            return readLine();
        } catch (IOException e) {
            return "";
        }
    }
}