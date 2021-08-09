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
    String currentUsername;

    public SavedGameRecyclerAdapter(List<SavedGame> savedGames, String currentUsername) {
        this.savedGames = savedGames;
        this.currentUsername = currentUsername;
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
        String username = savedGame.getUsername();
        if (username.equals(currentUsername)) {
            username = "You";
        }
        holder.didwinText.setText(savedGame.getDid_win() ? username + " won!" : username + " lost!");
        holder.timeText.setText(savedGame.getTime_completed_formatted());
        holder.numQuestionsText.setText(Integer.toString(savedGame.getNum_questions()) + "Q");
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

    class SavedGameViewHolder extends RecyclerView.ViewHolder {
        TextView didwinText, timeText, numQuestionsText;

        public SavedGameViewHolder(@NonNull View itemView) {
            super(itemView);
            didwinText = (TextView) itemView.findViewById(R.id.savedgame_didwin_textview);
            timeText = (TextView) itemView.findViewById(R.id.savedgame_time_textview);
            numQuestionsText = (TextView) itemView.findViewById(R.id.savedgame_numQuestions_textview);
        }
    }
}

