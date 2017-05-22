package com.example.hazard.telemedicine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hazard.telemedicine.R;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    public TextView complaintTitle, username, date;
    private TaskViewHolder.ClickListener mClickListener;


    public TaskViewHolder(View itemView) {
        super(itemView);
        complaintTitle = (TextView) itemView.findViewById(R.id.doctor_task_complaint_title);
        username = (TextView) itemView.findViewById(R.id.doctor_task_username);
        date = (TextView) itemView.findViewById(R.id.doctor_task_date);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });
    }

    //Interface to send callbacks...
    public interface ClickListener{
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(TaskViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
