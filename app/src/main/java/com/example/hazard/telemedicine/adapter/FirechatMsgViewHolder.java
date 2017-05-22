package com.example.hazard.telemedicine.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hazard.telemedicine.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FirechatMsgViewHolder extends RecyclerView.ViewHolder {

    public TextView msgTextView;
    public TextView userTextView;
    public CircleImageView userImageView;
    private FirechatMsgViewHolder.ClickListener mClickListener;

    public FirechatMsgViewHolder(View itemView) {
        super(itemView);
        msgTextView = (TextView) itemView.findViewById(R.id.msgTextView);
        userTextView = (TextView) itemView.findViewById(R.id.userTextView);
        userImageView = (CircleImageView) itemView.findViewById(R.id.userImageView);

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

    public void setOnClickListener(FirechatMsgViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }

}