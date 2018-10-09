package com.android.testcode.features.ui.listusers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.testcode.R;
import com.android.testcode.data.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> items;
    private int itemLayout;

    public UserAdapter(List<User> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new UserViewHolder(v);
    }

    @Override public void onBindViewHolder(final UserViewHolder holder, int position) {
        final User item = items.get(position);
        holder.itemView.setTag(item);
        holder.text.setText(item.getName());
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public void add(User item, int position) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void addAll(List<User> users) {
        items.clear();
        items.addAll(users);
        notifyDataSetChanged();
    }

    public void remove(User item) {
        int position = items.indexOf(item);
        items.remove(position);
        notifyItemRemoved(position);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public UserViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.userName);
        }

    }
}