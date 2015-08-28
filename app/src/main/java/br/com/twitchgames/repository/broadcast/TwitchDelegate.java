package br.com.twitchgames.repository.broadcast;

import br.com.twitchgames.model.TwitchResult;

public interface TwitchDelegate {
    void success(TwitchResult twitchResult);
}
