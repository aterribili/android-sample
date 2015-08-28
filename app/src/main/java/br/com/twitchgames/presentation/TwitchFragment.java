package br.com.twitchgames.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.twitchgames.R;
import br.com.twitchgames.model.TwitchResult;
import br.com.twitchgames.presentation.adapter.TwitchListAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TwitchFragment extends Fragment {
    @Bind(R.id.order_message_list) RecyclerView recyclerView;

    public static TwitchFragment newInstance(TwitchResult twitchResult) {
        TwitchFragment twitchFragment = new TwitchFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("twitch_result", twitchResult);
        twitchFragment.setArguments(bundle);

        return twitchFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_twitch, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            ButterKnife.bind(this, view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());

            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            TwitchResult twitchResult = (TwitchResult) getArguments().getSerializable("twitch_result");
            recyclerView.setAdapter(new TwitchListAdapter(twitchResult, getActivity()));
        }
    }
}
