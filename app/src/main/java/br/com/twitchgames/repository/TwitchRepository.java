package br.com.twitchgames.repository;

import android.content.Context;

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
    private final Context context;

    public TwitchRepository(Context context) {
        this.context = context;
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
        RepositoryBroadcast.twitchReceived(twitchResult, context);
    }
}
