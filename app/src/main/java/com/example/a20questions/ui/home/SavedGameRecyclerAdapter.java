package com.example.a20questions.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20questions.R;
import com.example.a20questions.data.SavedGame;

import java.util.List;

public class SavedGameRecyclerAdapter extends RecyclerView.Adapter<SavedGameRecyclerAdapter.SavedGameViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<SavedGame> savedGames = null;

    public SavedGameRecyclerAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        //this.savedGames = savedGames;
    }

    @NonNull
    @Override
    public SavedGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View savedgameView = layoutInflater.inflate(R.layout.saved_game_row_item, parent, false);
        return new SavedGameViewHolder(savedgameView);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedGameViewHolder holder, int position) {
        SavedGame savedGame = savedGames.get(position);

        Log.d("holder", holder.toString());
        Log.d("position", Integer.toString(position));
        Log.d("savedgames size", Integer.toString(savedGames.size()));
        Log.d("savedgames[0]", savedGames.get(0).getQuestions_and_answers());

        //holder.didwinText.setText(savedGame.getDid_win() ? "You won!" : "You lost!");
        //holder.timeText.setText(savedGame.getTime_completed_formatted());
    }

    @Override
    public int getItemCount() {
        if(savedGames != null)  {
            return savedGames.size();
        }
        else {
            return 0;
        }
    }

    public void setSavedGamesList(List<SavedGame> savedGames) {
        this.savedGames = savedGames;
        notifyDataSetChanged();
    }

    class SavedGameViewHolder extends RecyclerView.ViewHolder {
        TextView didwinText, timeText;

        public SavedGameViewHolder(@NonNull View itemView) {
            super(itemView);
            didwinText = (TextView) itemView.findViewById(R.id.savedgame_didwin_textview);
            timeText = (TextView) itemView.findViewById(R.id.savedgame_time_textview);
        }
    }
}

