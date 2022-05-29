package com.example.translator;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.net.URLEncoder;

public class Http {
    private static final String BASE_URL = "https://google-translate1.p.rapidapi.com/language/translate/v2";
    private static final String KEY = "YOUR_KEY_HERE";


    private static AsyncHttpClient client = new AsyncHttpClient();


    public static void post(String transText,String sourceLang, String destLang, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("X-RapidAPI-Host", "google-translate1.p.rapidapi.com");
        client.addHeader("X-RapidAPI-Key", "acf15d563bmshcf693a1e2488255p10f893jsn0beefc03489f");
        client.addHeader("Content-Type", "application/x-www-form-urlencoded");
        RequestParams params = new RequestParams();
        params.put("q", transText);
        params.put("source", sourceLang);
        params.put("target", destLang);
        params.setContentEncoding("application/x-www-form-urlencoded");
        client.post(BASE_URL, params, responseHandler);
    }

    private static String makeKeyChunk(String key) {
        return "key=" + KEY;
    }

    private static String makeTransChunk(String transText) {
        String encodedText = URLEncoder.encode(transText);
        return "&q=" + encodedText;
    }

    private static String langSource(String langSource) {
        return "&source=" + langSource;
    }

    private static String langDest(String langDest) {
        return "&target=" + langDest;

    }

    private static String getAbsoluteUrl(String transText, String sourceLang, String destLang) {
        String apiUrl = BASE_URL + makeKeyChunk(KEY) + makeTransChunk(transText) + langSource(sourceLang) + langDest(destLang);
        return apiUrl;
    }
}
