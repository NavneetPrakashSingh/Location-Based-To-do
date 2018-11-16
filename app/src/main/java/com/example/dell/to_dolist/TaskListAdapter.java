/*
* Created by Sharon Alva on 30/08/2018
*https://developer.android.com/guide/topics/ui/layout/recyclerview
* */

package com.example.dell.to_dolist;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskName, taskContent, taskId;
        public RelativeLayout relativelayout;

        private TaskViewHolder(View itemView) {
            super(itemView);
            taskId = itemView.findViewById(R.id.task_id);
            taskName = itemView.findViewById(R.id.taskName);
            taskContent = itemView.findViewById(R.id.task_description);
            relativelayout = itemView.findViewById(R.id.todo_list);
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
        holder.taskName.setText(current.getTitle());
        holder.taskContent.setText(current.getContent());

        holder.relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.relativelayout.getContext(), Update.class);
                String message = String.valueOf(current.getId());
                intent.putExtra("id", message);
                holder.relativelayout.getContext().startActivity(intent);
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
}


