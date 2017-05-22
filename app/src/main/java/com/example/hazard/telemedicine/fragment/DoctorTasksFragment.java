package com.example.hazard.telemedicine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hazard.telemedicine.R;
import com.example.hazard.telemedicine.logic.model.DoctorTask;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DoctorTasksFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_doctor_tasks;
    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<DoctorTask, TaskViewHolder>
            doctorTasksAdapter;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private View view;

    public static DoctorTasksFragment getInstance() {
        Bundle args = new Bundle();
        DoctorTasksFragment fragment = new DoctorTasksFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private static class TaskViewHolder extends RecyclerView.ViewHolder {

        public TextView complaintTitle, username, date;

        public TaskViewHolder(View itemView) {
            super(itemView);
            complaintTitle = (TextView) itemView.findViewById(R.id.doctor_task_complaint_title);
            username = (TextView) itemView.findViewById(R.id.doctor_task_username);
            date = (TextView) itemView.findViewById(R.id.doctor_task_date);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        initRecycleView();

        return view;
    }

    private void initRecycleView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.doctor_tasks_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        putTasksFromFirebase();
        recyclerView.setLayoutManager(layoutManager);
        //TODO recyclerView.setAnimation(...);
        recyclerView.setAdapter(doctorTasksAdapter);
    }

    private void putTasksFromFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        doctorTasksAdapter = new FirebaseRecyclerAdapter<DoctorTask, TaskViewHolder>(
                DoctorTask.class,
                R.layout.item_doctor_task,
                TaskViewHolder.class,
                databaseReference.child("doctor_tasks")
        ) {
            @Override
            protected void populateViewHolder(TaskViewHolder viewHolder, DoctorTask task, int position) {
                viewHolder.complaintTitle.setText(task.getComplaintTitle());
                viewHolder.username.setText(task.getUsername());
                viewHolder.date.setText(task.getDate());
            }
        };
    }
}
