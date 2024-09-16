package utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageUtils {

    // Method to download an image
    public static void downloadImage(String imgUrl, String destinationFile) throws IOException {
        URL url = new URL(imgUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");

        // Open an input stream to read the image data
        InputStream inputStream = httpConn.getInputStream();

        // Write the input stream data to a file in the target folder
        Files.copy(inputStream, Paths.get(destinationFile));
        
        // Close the input stream
        inputStream.close();
    }
}
