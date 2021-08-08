package com.example.a20questions;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScoresRecyclerAdapter extends RecyclerView.Adapter<ScoresRecyclerAdapter.ScoresViewHolder> {
    List<String> data;
    List<String> ans;
    public ScoresRecyclerAdapter(List<String>data,List<String>answer) {
        this.data = data;
        this.ans = answer;

    }

    @NonNull
    @Override
    public ScoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.row_item,parent,false);
        ScoresViewHolder svh = new ScoresViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoresViewHolder holder, int position) {

        //  holder.gamecount
        //  holder.win_value
        //  holder.lose_value
        /*
        holder.Text_Q.setText(data.get(position));
        holder.Text_A.setText(ans.get(position));
        holder.Q_number.setText(String.valueOf(position+1));
        */
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    class ScoresViewHolder extends RecyclerView.ViewHolder {
        // TextView ; //scores

        public ScoresViewHolder(@NonNull View itemView) {
            super(itemView);

            //  gamecount = itemView.findViewByID(R.id.g_count);
            //
            //  holder.gamecount
            //  holder.win_value
            //  holder.lose_value

            /*
            Text_Q = itemView.findViewById(R.id.qText);
            Text_A = itemView.findViewById(R.id.a_text);
            Q_number = itemView.findViewById(R.id.Text_count);
            */

        }
    }
}




