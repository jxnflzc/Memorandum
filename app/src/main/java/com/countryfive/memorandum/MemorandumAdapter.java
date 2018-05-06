package com.countryfive.memorandum;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MemorandumAdapter extends RecyclerView.Adapter<MemorandumAdapter.ViewHolder> {
    private List<Memorandum> mMemorandum;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView memorandumName, saveTime;
        View memorandumView;

        public ViewHolder(View view){
            super(view);
            memorandumView = view;
            memorandumName = view.findViewById(R.id.ItemMemorandum);
            //saveTime = view.findViewById(R.id.TxtShowTime);
        }
    }

    public MemorandumAdapter(List<Memorandum> memorandums){
        mMemorandum = memorandums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memorandum, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.memorandumView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Memorandum memorandum = mMemorandum.get(position);

                Intent gotoChangeMemorandum = new Intent(parent.getContext(), ChangeMemorandumActivity.class);
                gotoChangeMemorandum.putExtra("id", memorandum.getId());
                gotoChangeMemorandum.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                parent.getContext().startActivity(gotoChangeMemorandum);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Memorandum memorandum = mMemorandum.get(position);
        holder.memorandumName.setText(memorandum.getMemorandumName());
        //holder.saveTime.setText(memorandum.getSaveDate());
    }

    @Override
    public int getItemCount() {
        return mMemorandum.size();
    }
}
