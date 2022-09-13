package util;

import base.Interface.ILogger;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ConfigUtil implements ILogger {

    Map<String, String> propMap = new HashMap<>();

    /**
     * Load properties from config.properties file as a Map
     * @return
     */
    public Map<String, String> getProperties(String... properties) {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                log.info("Sorry, unable to find config.properties");
                return null;
            }
            prop.load(input);
            for (String args: properties) {
                propMap.put(args, prop.getProperty(args));
            }
            return propMap;
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    /**
     * Load All properties from Desired path
     * @param filePath
     * @return Properties
     */
    public Properties getAllProperties(String filePath) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            prop.load(input);
        }catch (IOException e){
            log.error(e.getMessage());
        }
        return prop;
    }

    /**
     * get a file from the resources folder
     * @param fileName
     * @return
     */
    public InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }


    /**
     * The resource URL is not working in the JAR
     * If we try to access a file that is inside a JAR,
     * It throws NoSuchFileException (linux), InvalidPathException (Windows)
     * Resource URL Sample: file:java-io.jar!/json/file1.json
     * @param fileName
     * @return
     * @throws URISyntaxException
     */
    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            /*failed if files have whitespaces or special characters, will return new File(resource.getFile());*/
            return new File(resource.toURI());
        }
    }


    /**
     * print input stream
     * @param is
     */
    private static void printInputStream(InputStream is) {
        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Print File
     * @param file
     */
    private static void printFile(File file) {
        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
