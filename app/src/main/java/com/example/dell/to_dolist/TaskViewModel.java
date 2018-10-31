/*Created by Sharon Alva on 30/10/2018
* https://developer.android.com/topic/libraries/architecture/viewmodel
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

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.dell.to_dolist.db.model.Task;

import java.util.List;


public class TaskViewModel extends AndroidViewModel {

    private TaskRepository mRepository;
    private LiveData<List<TaskDisplay>> mAllTasks;

    public TaskViewModel (Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTasks = mRepository.getAllTasks();
    }

    LiveData<List<TaskDisplay>> getmAllTasks() { return mAllTasks; }

    public void insert(Task task) { mRepository.insert(task); }
}