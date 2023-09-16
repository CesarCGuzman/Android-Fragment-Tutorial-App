package com.example.fragment_tutorial;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.fragment_tutorial.databinding.FragmentEducationSelectionBinding;

public class EducationSelectionFragment extends Fragment {
    FragmentEducationSelectionBinding binding;

    public EducationSelectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEducationSelectionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Education Selection");

        binding.buttonCancelDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.cancelEduSelection();
            }
        });

        binding.buttonSubmitDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int checkedID = binding.radioGroup.getCheckedRadioButtonId();
                if(checkedID == -1){
                    Toast.makeText(getActivity(), "Make a selection please", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton radioButton = binding.radioGroup.findViewById(checkedID);
                    String selectedEdu = radioButton.getText().toString();
                    mListener.sendSelectedEdu(selectedEdu);
                }
            }
        });
    }

    EduSelectionListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (EduSelectionListener) context;
    }

    public interface EduSelectionListener{
        void cancelEduSelection();
        void sendSelectedEdu(String education);
    }
}