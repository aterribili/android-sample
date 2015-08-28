package br.com.twitchgames.repository.client.converter;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Scanner;

import br.com.twitchgames.model.TwitchResult;
import br.com.twitchgames.repository.dao.TwitchDAO;
import retrofit.converter.ConversionException;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class TwitchConverter implements retrofit.converter.Converter {
    private final Context context;

    public TwitchConverter(Context context) {
        this.context = context;
    }

    @Override
    public TwitchResult fromBody(TypedInput body, Type type) throws ConversionException {
        String charset = "UTF-8";
        if (body.mimeType() != null) {
            charset = MimeUtil.parseCharset(body.mimeType());
        }

        try {
            InputStreamReader reader = new InputStreamReader(body.in(), charset);
            JSONObject jsonObject = new JSONObject(convertStreamToString(reader));
            new TwitchDAO(context).save(jsonObject);

            return new TwitchResult(jsonObject);
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
