package com.example.contactapp.fragments.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapp.R;
import com.example.contactapp.fragments.update.UpdateFragment;
import com.example.contactapp.model.User;

import java.util.ArrayList;
import java.util.List;



public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<User> userList = new ArrayList<>();




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User currentItem = userList.get(position);

        TextView tvId = holder.itemView.findViewById(R.id.tvId);
        tvId.setText(String.valueOf(currentItem.id));

        TextView tvFirstName = holder.itemView.findViewById(R.id.tvFirstName);
        tvFirstName.setText(String.valueOf(currentItem.firstName));

        TextView tvLastName = holder.itemView.findViewById(R.id.tvLastName);
        tvLastName.setText(String.valueOf(currentItem.lastName));

        TextView tvAge = holder.itemView.findViewById(R.id.tvAge);
        tvAge.setText(String.valueOf(currentItem.age));

        ConstraintLayout rowLayout = holder.itemView.findViewById(R.id.rowLayout);

        rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ListFragmentDirections.ActionListFragmentToUpdate action = ListFragmentDirections.actionListFragmentToUpdate(currentItem);
                //Navigation.findNavController(holder.itemView).navigate(action);
                ListFragmentDirections.ActionListFragmentToUpdateFragment action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem);
                Navigation.findNavController(holder.itemView).navigate(action);


            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    void setData(List<User> user){
        this.userList = user;
        notifyDataSetChanged();
    }
}
