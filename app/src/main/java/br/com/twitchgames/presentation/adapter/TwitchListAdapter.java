package br.com.twitchgames.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.twitchgames.R;
import br.com.twitchgames.model.TwitchGame;
import br.com.twitchgames.model.TwitchResult;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TwitchListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final TwitchResult twitchResult;
    private final Context context;

    public TwitchListAdapter(TwitchResult twitchResult, Context context) {
        this.twitchResult = twitchResult;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TwitchGameViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cell_twitch, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof TwitchGameViewHolder) {
            TwitchGameViewHolder holder = (TwitchGameViewHolder) viewHolder;
            TwitchGame twitchGame = twitchResult.getTop().get(i);
            Picasso.with(context).load(twitchGame.getGameImageUrl()).into(holder.photo);
            holder.text.setText(twitchGame.getGameName());
        }
    }

    @Override
    public int getItemCount() {
        return twitchResult.getTop().size();
    }

    class TwitchGameViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.photo)
        ImageView photo;
        @Bind(R.id.text)
        TextView text;

        public TwitchGameViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
