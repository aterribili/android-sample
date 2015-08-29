package br.com.twitchgames.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.twitchgames.R;
import br.com.twitchgames.model.TwitchResult;
import br.com.twitchgames.presentation.adapter.TwitchListAdapter;
import br.com.twitchgames.presentation.broadcast.LayoutDelegate;
import br.com.twitchgames.presentation.broadcast.LayoutType;
import br.com.twitchgames.presentation.broadcast.LayoutTypeBroadcast;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TwitchFragment extends Fragment implements LayoutDelegate {
    @Bind(R.id.order_message_list) RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private LayoutTypeBroadcast layoutTypeBroadcast;

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
        layoutTypeBroadcast = new LayoutTypeBroadcast(this, getActivity());

        if (savedInstanceState == null) {
            ButterKnife.bind(this, view);

            linearLayoutManager = new LinearLayoutManager(view.getContext());
            gridLayoutManager = new GridLayoutManager(getActivity(), 2);

            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            TwitchResult twitchResult = (TwitchResult) getArguments().getSerializable("twitch_result");
            recyclerView.setAdapter(new TwitchListAdapter(twitchResult));
        }
    }

    @Override
    public void changeLayoutToType(LayoutType layoutType) {
        if (layoutType == LayoutType.GRID)
            recyclerView.setLayoutManager(gridLayoutManager);
        else
            recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        layoutTypeBroadcast.unregister(getActivity());
    }
}
