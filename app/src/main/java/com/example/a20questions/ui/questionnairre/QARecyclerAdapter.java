package com.example.a20questions.ui.questionnairre;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20questions.R;

import java.util.List;

public class QARecyclerAdapter extends RecyclerView.Adapter<QARecyclerAdapter.ViewHolder> {

    List<QAPair> qaPairs;
    public QARecyclerAdapter(List<QAPair> qaPairs) {
        this.qaPairs = qaPairs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Text_Q.setText(qaPairs.get(position).getQ());
        holder.Text_A.setText(qaPairs.get(position).getA());
        holder.Q_number.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
       return qaPairs.size();
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

