package com.example.contactapp.fragments.add;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactapp.R;
import com.example.contactapp.model.User;
import com.example.contactapp.data.UserViewModel;


public class AddFragment extends Fragment {


    UserViewModel mUserViewModel;

    //UserViewModel mUserViewModel = new UserViewModel(requireActivity().getApplication());


    EditText etFirstName, etLastName, etAge;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        mUserViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);


        Button btnAdd = view.findViewById(R.id.btnAdd);
        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etAge = view.findViewById(R.id.etAge);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                Editable age = etAge.getText();
                insertDataToDatabase(firstName, lastName, age);
            }
        });



        return view;
    }



    private void insertDataToDatabase(String _firstName, String _lastName, Editable _age) {

        String firstName = _firstName;
        String lastName = _lastName;


        if(inputCheck(firstName,lastName,_age)){
            User user = new User();

            user.id = 0;
            user.firstName = firstName;
            user.lastName = lastName;
            user.age = Integer.parseInt(_age.toString());

            mUserViewModel.addUser(user);
            Toast.makeText(requireContext(), "Successfully added!",Toast.LENGTH_SHORT).show();
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.action_addFragment_to_listFragment);

        }
        else{
            Toast.makeText(requireContext(), "Fill out all fields",Toast.LENGTH_SHORT).show();
        }

    }

    private boolean inputCheck(String _firstName, String _lastName, Editable _age){
        return !(TextUtils.isEmpty(_firstName) && TextUtils.isEmpty(_lastName) && TextUtils.isEmpty(_age.toString()));

    }
}