package br.com.twitchgames.model;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.twitchgames.fixture.TwicthGameFixture;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class TwitchGameTests {
    TwitchGame twitchGame;

    @Before
    public void setup() throws JSONException {
        twitchGame = new TwitchGame(new JSONObject(TwicthGameFixture.GAME));
    }

    @Test
    public void shouldValidateTwitchGameRootAttributes() {
        Assert.assertEquals(twitchGame.getChannels(), 305);
        Assert.assertEquals(twitchGame.getViewers(), 23873);
        Assert.assertNotNull(twitchGame.getGame());
    }

    @Test
    public void shouldValidateTwitchGameGameAttribute() {
        Game game = twitchGame.getGame();

        Assert.assertEquals(game.getId(), 32399);
        Assert.assertEquals(game.getGiantbombId(), 36113);
        Assert.assertEquals(game.getName(), "Counter-Strike: Global Offensive");
    }

    @Test
    public void shouldValidateTwitchGameBox() {
        Game game = twitchGame.getGame();

        Assert.assertEquals(game.getBox().getLarge(), "http://static-cdn.jtvnw.net/ttv-boxart/Counter-Strike:%20Global%20Offensive-272x380.jpg");
        Assert.assertEquals(game.getBox().getMedium(), "http://static-cdn.jtvnw.net/ttv-boxart/Counter-Strike:%20Global%20Offensive-136x190.jpg");
        Assert.assertEquals(game.getBox().getSmall(), "http://static-cdn.jtvnw.net/ttv-boxart/Counter-Strike:%20Global%20Offensive-52x72.jpg");
        Assert.assertEquals(game.getBox().getTemplate(), "http://static-cdn.jtvnw.net/ttv-boxart/Counter-Strike:%20Global%20Offensive-{width}x{height}.jpg");
    }

    @Test
    public void shouldValidateTwitchGameLogo() {
        Game game = twitchGame.getGame();

        Assert.assertEquals(game.getLogo().getLarge(), "http://static-cdn.jtvnw.net/ttv-logoart/Counter-Strike:%20Global%20Offensive-240x144.jpg");
        Assert.assertEquals(game.getLogo().getMedium(), "http://static-cdn.jtvnw.net/ttv-logoart/Counter-Strike:%20Global%20Offensive-120x72.jpg");
        Assert.assertEquals(game.getLogo().getSmall(), "http://static-cdn.jtvnw.net/ttv-logoart/Counter-Strike:%20Global%20Offensive-60x36.jpg");
        Assert.assertEquals(game.getLogo().getTemplate(), "http://static-cdn.jtvnw.net/ttv-logoart/Counter-Strike:%20Global%20Offensive-{width}x{height}.jpg");
    }
}
