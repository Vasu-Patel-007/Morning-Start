package com.example.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycler_adapter extends RecyclerView.Adapter<recycler_adapter.MyViewHolder> {
    private ArrayList<button_name> buttonList;
    private RecyclerViewClickListener listener;

    public recycler_adapter(ArrayList<button_name> temp, RecyclerViewClickListener listener){
        this.buttonList = temp;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Button button;

        public MyViewHolder(final View view){
            super(view);
            button = view.findViewById(R.id.button1);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onclick(view,getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public recycler_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.breakfast_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recycler_adapter.MyViewHolder holder, int position) {
        String name = buttonList.get(position).getName();
        holder.button.setText(name);
    }

    @Override
    public int getItemCount() {
        return buttonList.size();
    }

    public interface RecyclerViewClickListener{
        void onclick(View v, int position );
    }
}
