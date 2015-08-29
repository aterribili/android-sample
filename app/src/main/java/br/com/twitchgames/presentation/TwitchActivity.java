package br.com.twitchgames.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioGroup;

import javax.inject.Inject;

import br.com.twitchgames.R;
import br.com.twitchgames.infra.TwitchApplication;
import br.com.twitchgames.model.TwitchResult;
import br.com.twitchgames.presentation.broadcast.LayoutType;
import br.com.twitchgames.presentation.broadcast.LayoutTypeBroadcast;
import br.com.twitchgames.repository.TwitchRepository;
import br.com.twitchgames.repository.broadcast.RepositoryBroadcast;
import br.com.twitchgames.repository.broadcast.TwitchDelegate;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TwitchActivity extends AppCompatActivity implements TwitchDelegate {
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.menu_filter) RadioGroup radioGroup;
    @Inject TwitchRepository twitchRepository;
    RepositoryBroadcast repositoryBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitch);
        ButterKnife.bind(this);
        TwitchApplication.getTwitchApplication().getComponent().inject(this);

        toolbar.setTitle("TwitchGames");
        repositoryBroadcast = new RepositoryBroadcast(this);
        twitchRepository.getTwitchGames();

        radioGroup.setOnCheckedChangeListener(new RadioListener());
    }

    @Override
    public void success(TwitchResult twitchResult) {
        repositoryBroadcast.unregister();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main, TwitchFragment.newInstance(twitchResult))
                .commitAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repositoryBroadcast.unregister();
    }

    private class RadioListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.grid)
                LayoutTypeBroadcast.changeLayoutToType(LayoutType.GRID, TwitchActivity.this);
            else
                LayoutTypeBroadcast.changeLayoutToType(LayoutType.LIST, TwitchActivity.this);
        }
    }
}
