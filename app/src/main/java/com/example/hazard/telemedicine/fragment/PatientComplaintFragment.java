package com.example.hazard.telemedicine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hazard.telemedicine.R;
import com.example.hazard.telemedicine.logic.ProfileSingleton;
import com.example.hazard.telemedicine.logic.model.DoctorTask;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class PatientComplaintFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_complaint;
    private EditText complaintTitleET;
    private EditText complaintET;
    private Button sendComplaintBtn;
    private DatabaseReference databaseReference;
    private View view;

    public static PatientComplaintFragment getInstance() {
        Bundle args = new Bundle();
        PatientComplaintFragment fragment = new PatientComplaintFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        initView();
        sendComplaintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(complaintTitleET.getText().toString(), "") &&
                        Objects.equals(complaintET.getText().toString(), "")) {
                    Toast.makeText(getContext(), "Введите заголовок и жалобу", Toast.LENGTH_SHORT).show();
                } else {
                    Date date = new Date();
                    SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");

                    sendComplaintToFirebase(
                            complaintTitleET.getText().toString(),
                            complaintET.getText().toString(),
                            ProfileSingleton.getInstance().getUsername(),
                            formatDate.format(date)
                    );

                    Toast.makeText(getContext(), "Ваша жалоба успешно отправлена", Toast.LENGTH_SHORT).show();
                    complaintTitleET.setText("");
                    complaintET.setText("");
                }
            }
        });

        return view;
    }

    private void sendComplaintToFirebase(String complaintTitle, String complaint, String username, String date) {
        DoctorTask doctorTask = new DoctorTask(complaintTitle, complaint, username, date);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("doctor_tasks").push().setValue(doctorTask);
    }

    private void initView() {
        complaintTitleET = (EditText) view.findViewById(R.id.complaint_title_edit_text);
        complaintET = (EditText) view.findViewById(R.id.complaint_edit_text);
        sendComplaintBtn = (Button) view.findViewById(R.id.send_complaint_button);
    }
}
