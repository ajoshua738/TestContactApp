package com.example.contactapp.fragments.update;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactapp.R;
import com.example.contactapp.data.UserViewModel;
import com.example.contactapp.model.User;


public class UpdateFragment extends Fragment {


    private UpdateFragmentArgs args;
    EditText firstNameUpdate, lastNameUpdate, ageUpdate;

    UserViewModel mUserViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        args = UpdateFragmentArgs.fromBundle(getArguments());
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        mUserViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);


        firstNameUpdate = view.findViewById(R.id.etFirstNameUpdate);
        firstNameUpdate.setText(args.getCurrentUser().firstName);

        lastNameUpdate = view.findViewById(R.id.etLastNameUpdate);
        lastNameUpdate.setText(args.getCurrentUser().lastName);

        ageUpdate = view.findViewById(R.id.etAgeUpdate);
        ageUpdate.setText(String.valueOf(args.getCurrentUser().age));


        Button btnUpdate = view.findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItem();


            }
        });


        setHasOptionsMenu(true);
        return view;
    }

    void updateItem(){
        String firstName = firstNameUpdate.getText().toString();
        String lastName = lastNameUpdate.getText().toString();
        int age = Integer.parseInt(ageUpdate.getText().toString());

        if(inputCheck(firstName,lastName, ageUpdate.getText())){
            User user = new User(args.getCurrentUser().id,firstName,lastName,age);
            mUserViewModel.updateUser(user);
            Toast.makeText(requireContext(), "Successfully updated!",Toast.LENGTH_SHORT).show();
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.action_updateFragment_to_listFragment);

        }
        else{
            Toast.makeText(requireContext(), "Fill out all fields",Toast.LENGTH_SHORT).show();
        }

    }

    private boolean inputCheck(String _firstName, String _lastName, Editable _age){
        return !(TextUtils.isEmpty(_firstName) && TextUtils.isEmpty(_lastName) && TextUtils.isEmpty(_age.toString()));

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.delete_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_delete){
            deleteUser();
        }

        return super.onOptionsItemSelected(item);
    }

        private void deleteUser() {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setTitle("Delete User?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when OK button is clicked
                        mUserViewModel.deleteUser(args.getCurrentUser());
                        Toast.makeText(requireContext(), "Successfully removed " + args.getCurrentUser().firstName, Toast.LENGTH_SHORT).show();
                        NavController navController = Navigation.findNavController(requireView());
                        navController.navigate(R.id.action_updateFragment_to_listFragment);

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when Cancel button is clicked
                    }
                }).setMessage("Are you sure you want to delete "+args.getCurrentUser().firstName+"?")
                .create().show();
    }
}