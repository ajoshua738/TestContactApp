package com.example.contactapp.fragments.list;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.contactapp.R;
import com.example.contactapp.model.User;
import com.example.contactapp.data.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class ListFragment extends Fragment {

    UserViewModel mUserViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        FloatingActionButton fabAdd = view.findViewById(R.id.fabAdd);


        ListAdapter adapter = new ListAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.rvList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        mUserViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        mUserViewModel.readAllData.observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setData(users);
            }
        });


        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_listFragment_to_addFragment);
            }
        });

        setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.delete_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_delete){
            deleteAllUsers();
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllUsers() {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setTitle("Delete All Users?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when OK button is clicked
                        mUserViewModel.deleteAllUsers();
                        Toast.makeText(requireContext(), "Successfully deleted all users", Toast.LENGTH_SHORT).show();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when Cancel button is clicked
                    }
                }).setMessage("Are you sure you want to delete all users?")
                .create().show();
    }
}