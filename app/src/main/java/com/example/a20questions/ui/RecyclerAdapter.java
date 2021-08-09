package com.example.a20questions.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20questions.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    List<String> data;
    List<String> ans;
    public RecyclerAdapter(List<String>data,List<String>answer) {
        this.data = data;
        this.ans = answer;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Text_Q.setText(data.get(position));
        holder.Text_A.setText(ans.get(position));
        holder.Q_number.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {

       return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Text_Q, Text_A, Q_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Text_Q = itemView.findViewById(R.id.qText);
            Text_A = itemView.findViewById(R.id.a_text);
            Q_number = itemView.findViewById(R.id.Text_count);

        }
    }
}

