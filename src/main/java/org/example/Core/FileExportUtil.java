package org.example.Core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FileExportUtil {

    private static final String DEFAULT_EXPORT_DIR = "test_results/";
    /**
     * Exports the provided content to a file in the default directory.
     *
     * @param fileName Name of the file.
     * @param content  List of strings representing file content.
     * @return The full path of the created file or null if an error occurs.
     */
    public static String exportToFile(String fileName, List<String> content) {
        return exportToFile(DEFAULT_EXPORT_DIR, fileName, content);
    }
    /**
     * Exports the provided content to a file in a specified directory.
     *
     * @param directory Directory where the file should be saved.
     * @param fileName  Name of the file (without extension).
     * @param content   List of strings representing file content.
     * @return The full path of the created file or null if an error occurs.
     */
    public static String exportToFile(String directory, String fileName, List<String> content) {
        // Ensure the directory exists
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Create a timestamped file name
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
        String filePath = directory + fileName + "_" + timestamp + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write export metadata
            writer.println("Export Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            writer.println("====================================");

            // Write the content to the file
            for (String line : content) {
                writer.println(line);
            }

            // Return the generated file path
            return filePath;
        } catch (IOException e) {
            // Print stack trace if an error occurs and return null
            e.printStackTrace();
            return null;
        }
    }
}