package br.com.twitchgames.infra;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.client.UrlConnectionClient;
import retrofit.converter.Converter;

public class TwitchRestAdapter {
    private final String mainUrl;

    public TwitchRestAdapter(String mainUrl) {
        this.mainUrl = mainUrl;
    }

    public <T> T create(java.lang.Class<T> service, Converter converter) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new TwitchClient())
                .setConverter(converter)
                .setEndpoint(mainUrl)
                .build();

        return restAdapter.create(service);
    }

    class TwitchClient extends UrlConnectionClient {
        @Override
        public Response execute(Request request) throws IOException {
            List<Header> headers = mapHeaders(request.getHeaders());

            request = new Request(request.getMethod(), request.getUrl(), headers, request.getBody());

            return super.execute(request);
        }

        private List<Header> mapHeaders(List<Header> headers) {
            List<Header> newHeaders = new ArrayList<>();
            newHeaders.addAll(headers);

            newHeaders.add(new Header("Accept", "application/vnd.twitchtv.v3+json"));

            return newHeaders;
        }
    }
}
