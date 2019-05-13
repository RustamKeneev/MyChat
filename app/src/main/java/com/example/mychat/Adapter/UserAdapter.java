package com.example.mychat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mychat.MessageActivity;
import com.example.mychat.Model.User;
import com.example.mychat.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUsers;

    private boolean ischat;

    public UserAdapter(Context mContext, List<User> mUsers, boolean ischat){
        this.mUsers = mUsers;
        this.mContext = mContext;
        this.ischat = ischat;



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item,viewGroup,false);
        return  new UserAdapter.ViewHolder( view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final User user = mUsers.get(position);
        viewHolder.username.setText(user.getUsername());
        if (user.getImageURL().equals("default")){
             viewHolder.profile_image.setImageResource(R.mipmap.ic_launcher_user);
        }else {
            Glide.with(mContext).load(user.getImageURL()).into(viewHolder.profile_image);
        }

        if (ischat){
            if (user.getStatus().equals("online")){
                viewHolder.image_on.setVisibility(View.VISIBLE);
                viewHolder.image_off.setVisibility(View.GONE);
            }else {
                viewHolder.image_on.setVisibility(View.GONE);
                viewHolder.image_off.setVisibility(View.VISIBLE);
            }
        }else {
            viewHolder.image_on.setVisibility(View.GONE);
            viewHolder.image_off.setVisibility(View.GONE);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid",user.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username;
        public ImageView profile_image;
        private ImageView image_on;
        private ImageView image_off;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
            image_on = itemView.findViewById(R.id.image_on);
            image_off = itemView.findViewById(R.id.image_off);

        }
    }
}
