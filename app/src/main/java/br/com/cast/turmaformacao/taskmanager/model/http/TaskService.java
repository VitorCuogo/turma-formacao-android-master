package br.com.cast.turmaformacao.taskmanager.model.http;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;

public final class TaskService {

    private static final String URL = "http://10.11.21.193:3000/api/v1/task";

    private TaskService(){
        super();
    }

    public static Task getTaskByWebId(){
        Task task = null;

        try {
            java.net.URL url = new URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            Log.i("getTaskByWebId", "Código de retorno da requisição de task: " + responseCode);
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = conn.getInputStream();

                ObjectMapper objectMapper = new ObjectMapper();
                task = objectMapper.readValue(inputStream, Task.class);

            }

        } catch (Exception e){
            Log.e(TaskService.class.getName(), "" + e.getMessage());
        }

        return task;
    }

    public static List<Task> getTaskWebId(){
        List<Task> tasks = new ArrayList<>();

        try {
            java.net.URL url = new URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if(responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Error code: " + responseCode);
            }

            InputStream inputStream = conn.getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();
            tasks = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Task.class));

            conn.disconnect();

        } catch (Exception e){
            e.printStackTrace();
        }

        return tasks;
    }
}
