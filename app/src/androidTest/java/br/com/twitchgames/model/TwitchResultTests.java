package br.com.twitchgames.model;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.twitchgames.fixture.TwichResultFixture;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class TwitchResultTests {
    TwitchResult twitchResult;

    @Before
    public void setup() throws JSONException {
        twitchResult = new TwitchResult(new JSONObject(TwichResultFixture.RESULT));
    }

    @Test
    public void shouldValidateTwitchResultRootAttributes() {
        Assert.assertEquals(twitchResult.getTop().size(), 2);
        Assert.assertEquals(twitchResult.getTotal(), 322);
    }
}
