package com.example.fragment_tutorial;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fragment_tutorial.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    public static final String TAG = "demo";
    private String selectedEdu;

    public void setSelectedEdu(String selectedEdu) {
        this.selectedEdu = selectedEdu;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home Fragment");
        binding.textViewLabel.setText("Edu:");

        if(selectedEdu == null){
            binding.textViewSelectedEdu.setText("Not Set");
        } else {
            binding.textViewSelectedEdu.setText(selectedEdu);
        }

        binding.buttonSubmit.findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.editTextName.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter a valid name", Toast.LENGTH_SHORT).show();
                } else if (selectedEdu == null) {
                    Toast.makeText(getActivity(), "Select Education", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        double age = Double.valueOf(binding.editTextAge.getText().toString());
                        Profile profile = new Profile(name, age, selectedEdu);
                        mListener.sendProfile(profile);
                    } catch (NumberFormatException ex){
                        Toast.makeText(getActivity(), "Enter a valid age", Toast.LENGTH_SHORT).show();
                    }
                }
                mListener.sendUsername(name);
            }
        });

        binding.buttonGoToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToSettings();
            }
        });

        binding.buttonSetEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToEduSelection();
            }
        });
    }

    HomeListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (HomeListener) context;

    }

    // Define interface in the fragment
    public interface HomeListener{
        void sendProfile(Profile profile);
        void sendUsername(String Username);
        void goToSettings();
        void goToEduSelection();
    }
}