import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://crypto-news-live.p.rapidapi.com/news/coindesk"))
                .headers(
                        "x-rapidapi-host", "crypto-news-live.p.rapidapi.com",
                        "x-rapidapi-key", "2620c20c64msh116641fd70a041ep1ece81jsn99636864edd0")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        try {
            ObjectMapper mapper = new ObjectMapper();
            News[] results = mapper.readValue(response.body(), News[].class);
            for(News news : results) {
                System.out.printf("%s - %s\n", news.title, news.url);
            }
        } catch(JsonParseException err) {
            System.out.println(err.getMessage());
        }
        
    }
}
