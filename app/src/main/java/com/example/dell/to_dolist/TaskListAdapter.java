/*
* Created by Sharon Alva on 30/08/2018
*https://developer.android.com/guide/topics/ui/layout/recyclerview
* */

package com.example.dell.to_dolist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.List;

/*
* Sample Input: Various elements related to the home page are combined
* Sample Output: Displays independent task on the homepage
* */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {
    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskName;
        public LinearLayout linearLayout;
        private ProgressBar progressBar;
        private Button deleteButton;
        private TaskViewHolder(View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            linearLayout = itemView.findViewById(R.id.todo_list);
            progressBar = itemView.findViewById(R.id.pbId);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    private final LayoutInflater mInflater;
    private List<TaskDisplay> mTasks;

    TaskListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TaskViewHolder holder, final int position) {

       final TaskDisplay current = mTasks.get(position);
        if(position % 2 == 0){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else{
            holder.linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.taskName.setText(current.getTitle());

        double percent= (current.getCount() * 100) / current.getTotalCount();
        holder.progressBar.setProgress( (int) percent );
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.linearLayout.getContext(), Update.class);
                String message = String.valueOf(current.getId());
                intent.putExtra("id", message);
                holder.linearLayout.getContext().startActivity(intent);
            }
        });
    }

    void setTasks(List<TaskDisplay> tasks){
        mTasks = tasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTasks != null)
            return mTasks.size();
        else return 0;
    }

    public TaskDisplay getTaskAtPosition (int position) {
        return mTasks.get(position);
    }
}