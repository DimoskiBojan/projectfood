package mk.ukim.finki.projectfood.util;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public final class HttpUtils {

    private HttpUtils(){
        throw new UnsupportedOperationException();
    }

    public static int getResponseStatusFromHttpUrl(String strUrl) throws IOException {
        // Getting the status code.
        URL url = new URL(strUrl);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        int statusCode = http.getResponseCode();
        http.disconnect();

        return statusCode;
    }

    public static JSONArray getJSONArrayFromUrl(String url) {
        JSONArray jsonArray = null;
        HttpURLConnection httpURLConnection = null;
        String jsonString = null;

        try {
            URL u = new URL(url.replace(" ", "%20"));
            httpURLConnection = (HttpURLConnection) u.openConnection();
            httpURLConnection.setRequestMethod("GET");
            if(httpURLConnection.getResponseCode() >= 400){
                return null;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + '\n');
            }
            jsonString = stringBuilder.toString();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            jsonArray = new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static JSONObject getJSONObjectFromUrl(String url) {
        JSONObject jsonObject = null;
        HttpURLConnection httpURLConnection = null;
        String jsonString = null;

        try {
            URL u = new URL(url.replace(" ", "%20"));
            httpURLConnection = (HttpURLConnection) u.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            if(httpURLConnection.getResponseCode() >= 400){
                return null;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + '\n');
            }
            jsonString = stringBuilder.toString();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
