package com.example.a20questions.ui.home;

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

    List<SavedGame> savedGames;

    public SavedGameRecyclerAdapter(List<SavedGame> savedGames) {
        this.savedGames = savedGames;
    }

    @NonNull
    @Override
    public SavedGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        return new SavedGameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedGameViewHolder holder, int position) {
        holder.didwinText.setText(savedGames.get(position).getDid_win() ? "You won!" : "You lost!");
        holder.timeText.setText(savedGames.get(position).getTime_completed_formatted());
    }

    @Override
    public int getItemCount() {
       return savedGames.size();
    }

    public void setSavedGamesList(List<SavedGame> savedGames) {
        this.savedGames = savedGames;
        notifyDataSetChanged();
    }

    class SavedGameViewHolder extends RecyclerView.ViewHolder {
        TextView didwinText, timeText;

        public SavedGameViewHolder(@NonNull View itemView) {
            super(itemView);
            didwinText = itemView.findViewById(R.id.savedgame_didwin_textview);
            timeText = itemView.findViewById(R.id.savedgame_time_textview);
        }
    }
}

