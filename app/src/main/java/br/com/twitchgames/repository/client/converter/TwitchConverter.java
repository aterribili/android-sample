package br.com.twitchgames.repository.client.converter;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Scanner;

import br.com.twitchgames.model.TwitchResult;
import retrofit.converter.ConversionException;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class TwitchConverter implements retrofit.converter.Converter {
    @Override
    public TwitchResult fromBody(TypedInput body, Type type) throws ConversionException {
        String charset = "UTF-8";
        if (body.mimeType() != null) {
            charset = MimeUtil.parseCharset(body.mimeType());
        }

        try {
            InputStreamReader reader = new InputStreamReader(body.in(), charset);

            return new TwitchResult(new JSONObject(convertStreamToString(reader)));
        } catch (IOException e) {
            Log.e("TwitchGames", e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new TwitchResult();
    }

    @Override
    public TypedOutput toBody(Object object) {
        return null;
    }

    private String convertStreamToString(InputStreamReader isr) {
        Scanner scanner = new Scanner(isr).useDelimiter("\\A");

        return scanner.hasNext() ? scanner.next() : "";
    }
}
