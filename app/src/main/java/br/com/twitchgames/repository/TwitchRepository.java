package br.com.twitchgames.repository;

import android.content.Context;

import javax.inject.Inject;

import br.com.twitchgames.infra.TwitchApplication;
import br.com.twitchgames.model.TwitchResult;
import br.com.twitchgames.repository.broadcast.RepositoryBroadcast;
import br.com.twitchgames.repository.broadcast.TwitchBroadcast;
import br.com.twitchgames.repository.broadcast.TwitchDelegate;
import br.com.twitchgames.repository.client.TwitchClient;
import br.com.twitchgames.repository.dao.TwitchDAO;
import br.com.twitchgames.utils.Connection;

public class TwitchRepository implements TwitchDelegate {
    private final TwitchClient twitchClient;
    private final TwitchDAO twitchDAO;
    private final TwitchBroadcast twitchBroadcast;
    @Inject Context context;

    public TwitchRepository() {
        TwitchApplication.getTwitchApplication().getComponent().inject(this);
        this.twitchClient = new TwitchClient(context);
        this.twitchDAO = new TwitchDAO(context);
        this.twitchBroadcast = new TwitchBroadcast(this, context);
    }

    public void getTwitchGames() {
        if (Connection.isAvailable(context)) {
            twitchClient.getGames();
        } else {
            twitchDAO.getCachedGames();
        }
    }

    @Override
    public void success(TwitchResult twitchResult) {
        this.twitchBroadcast.unregister(context);
        RepositoryBroadcast.twitchReceived(twitchResult);
    }
}
