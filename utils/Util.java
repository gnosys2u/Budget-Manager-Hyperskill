package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Util {

    /**
     * output to console (or specified stream)
     * call setupOut(desiredOutStream) before using pr/prln to use desiredOutStream,
     * otherwise System.out stream will be used
     */
    private static PrintStream out = null;
    public static void setOut(PrintStream o) {
        out = o;
    }

    private static PrintStream setupOut() {
        if (out == null) {
            out = System.out;
        }
        return out;
    }
    public static void pr(String s) {
        setupOut().print(s);
        if (logAll) {
            logit(s);
        }
    }

    public static void prln(String s) {
        setupOut().println(s);
        if (logAll) {
            logitln(s);
        }
    }



    /**
     * get input from console in (or specified stream)
     * call setupScanner(desiredInStream) before using scanInt/scanString/scanLine to use desiredInStream,
     * otherwise System.in stream will be used
     */
    private static Scanner scanner = null;
    public static void setScanner(Scanner s) {
        scanner = s;
    }

    private static Scanner setupScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static int scanInt() {
        int i = setupScanner().nextInt();
        if (logAll) {
            logitln("scanInt:" + i);
        }
        return i;
    }

    public static double scanDouble() {
        double d = setupScanner().nextDouble();
        if (logAll) {
            logitln("scanDouble:" + d);
        }
        return d;
    }

    public static String scanString() {
        String s = setupScanner().next();
        if (logAll) {
            logitln("scanString:" + s);
        }
        return s;
    }

    public static String scanLine() {
        String s = setupScanner().nextLine();
        if (logAll) {
            logitln("scanLine:" + s);
        }
        return s;
    }

    public static boolean scanHasNext() {
        return setupScanner().hasNext();
    }

    public static boolean scanHasNextLine() {
        return setupScanner().hasNextLine();
    }

    /**
     * Serialize the given object to the file
     */
    public static void serializeToFile(Object obj, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }

    /**
     * Deserialize to an object from the file
     */
    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }

    /**
     * output to logfile
     * call setupLogit(PATH) to set the file logit will write to
     * call setLogitPrefix(STRING) before using logit to set a prefix
     */
    private static boolean logAll = false;
    private static String logitPrefix = null;
    private static String logitPath = "../../../loggy.txt";
    public static void logitln(String message) {
        try (FileWriter fileWriter = new FileWriter(logitPath, true)){
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(logitPrefix == null ? message : logitPrefix + message);
        } catch (Exception ex) {
        }
    }

    public static void logit(String message) {
        try (FileWriter fileWriter = new FileWriter(logitPath, true)){
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(logitPrefix == null ? message : logitPrefix + message);
        } catch (Exception ex) {
        }
    }

    public static void setLogitPrefix(String prefix) {
        logitPrefix = prefix;
    }
    public static void setupLogit(String path) {
        logitPath = path;
    }

    public static void prlnLogged(String message) {
        prln(message);
        logitln(message);
    }

    public static void prLogged(String message) {
        pr(message);
        logit(message);
    }

    public static void setLogAll(boolean logAllIn) {
        logAll = logAllIn;
    }

    /**
     * read a file into a string
     */
    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

}
