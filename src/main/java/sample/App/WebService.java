package sample.App;

import com.google.gson.Gson;
import javafx.concurrent.Task;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import sample.Exception.NoConnectionException;
import sample.Exception.WrongArgumentException;

import java.io.IOException;

public class WebService {

    private static HttpResponse response;
    public static String getResponse() throws IOException, NoConnectionException {

        HttpClient client = new DefaultHttpClient();
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                HttpGet request = new HttpGet(ConfigReader.getDataFromProperty("api_url"));
                response = client.execute(request);
                return null;
            }
        };
        new Thread(task).start();
        task.run();
        String strResponse = EntityUtils.toString(response.getEntity());
        Gson gson = new Gson();
        ApiResponse apiResponse = gson.fromJson(strResponse, ApiResponse.class);
        try {
            return apiResponse.getCurrentDateTime();
        } catch (WrongArgumentException e) {
            GUI.logger.error("Exception in getResponse method.", e);
            return null;
        }
    }
}