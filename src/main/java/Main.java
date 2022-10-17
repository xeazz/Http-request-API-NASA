
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    private static final String URL =
            "https://api.nasa.gov/planetary/apod?api_key=2XUVgpfdX0gNMaKxoZLCzeSoBrThBpM5dY9nOL1C";
    public static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) throws IOException {
        String parseJson = requestJson();
        String url = parseFromJson(parseJson).getUrl();
        requestPicture(url);
    }

    public static Answer parseFromJson(String body) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(body, Answer.class);
    }

    public static String requestJson() throws IOException {
        final HttpUriRequest httpGet = new HttpGet(URL);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            return new String(response.getEntity().getContent().readAllBytes(),
                    StandardCharsets.UTF_8);
        }
    }

    public static void requestPicture(String url) throws IOException {
        final HttpUriRequest httpGet2 = new HttpGet(url);
        CloseableHttpResponse pictureResponse = httpClient.execute(httpGet2);
        InputStream inputStream = pictureResponse.getEntity().getContent();
        String[] name = url.split("/");
        try (FileOutputStream outputStream = new FileOutputStream(name[name.length - 1])) {
            byte[] bytes = inputStream.readAllBytes();
            outputStream.write(bytes, 0, bytes.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

