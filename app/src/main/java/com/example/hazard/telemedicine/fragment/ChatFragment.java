package com.example.hazard.telemedicine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hazard.telemedicine.R;
import com.example.hazard.telemedicine.activity.AuthorizationActivity;
import com.example.hazard.telemedicine.logic.ProfileSingleton;
import com.example.hazard.telemedicine.logic.model.ChatMessage;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.hazard.telemedicine.Const.DEFAULT_NAME;


public class ChatFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_chat;
    private DatabaseReference simpleFirechatDatabaseReference;
    private FirebaseRecyclerAdapter<ChatMessage, FirechatMsgViewHolder>
            firebaseAdapter;
    private RecyclerView messageRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;
    private Button sendButton;
    private EditText msgEditText;
    private View view;

    public static ChatFragment getInstance() {
        Bundle args = new Bundle();
        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private static class FirechatMsgViewHolder extends RecyclerView.ViewHolder {

        public TextView msgTextView;
        public TextView userTextView;
        public CircleImageView userImageView;

        public FirechatMsgViewHolder(View itemView) {
            super(itemView);
            msgTextView = (TextView) itemView.findViewById(R.id.msgTextView);
            userTextView = (TextView) itemView.findViewById(R.id.userTextView);
            userImageView = (CircleImageView) itemView.findViewById(R.id.userImageView);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        setHasOptionsMenu(true); //для logout

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        messageRecyclerView = (RecyclerView) view.findViewById(R.id.messageRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        messageRecyclerView.setLayoutManager(linearLayoutManager);

        simpleFirechatDatabaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAdapter = new FirebaseRecyclerAdapter<ChatMessage, FirechatMsgViewHolder>(
                ChatMessage.class,
                R.layout.item_chat_message,
                FirechatMsgViewHolder.class,
                simpleFirechatDatabaseReference.child("messages")) {
            @Override
            protected void populateViewHolder(FirechatMsgViewHolder viewHolder, ChatMessage friendlyMessage, int position) {
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                viewHolder.msgTextView.setText(friendlyMessage.getText());
                viewHolder.userTextView.setText(friendlyMessage.getName());
                if (friendlyMessage.getPhotoUrl() == null) {
                    viewHolder.userImageView
                            .setImageDrawable(ContextCompat
                                    .getDrawable(getContext(),
                                            R.drawable.ic_account_circle_black_24dp));
                } else {
                    Glide.with(getContext())
                            .load(friendlyMessage.getPhotoUrl())
                            .into(viewHolder.userImageView);
                }
            }
        };

        firebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                int chatMessageCount = firebaseAdapter.getItemCount();
                int lastVisiblePosition =
                        linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (chatMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    messageRecyclerView.scrollToPosition(positionStart);
                }
            }
        });
        messageRecyclerView.setLayoutManager(linearLayoutManager);
        messageRecyclerView.setAdapter(firebaseAdapter);

        initSend();
        return view;
    }


    private void initSend() {
        msgEditText = (EditText) view.findViewById(R.id.msgEditText);
        msgEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 0) {
                    sendButton.setEnabled(true);
                } else {
                    sendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        sendButton = (Button) view.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatMessage friendlyMessage = new
                        ChatMessage(msgEditText.getText().toString(),
                        ProfileSingleton.getInstance().getUsername(),
                        ProfileSingleton.getInstance().getPhotoUrl());
                simpleFirechatDatabaseReference.child("messages")
                        .push().setValue(friendlyMessage);
                msgEditText.setText("");
            }
        });
    }
}
