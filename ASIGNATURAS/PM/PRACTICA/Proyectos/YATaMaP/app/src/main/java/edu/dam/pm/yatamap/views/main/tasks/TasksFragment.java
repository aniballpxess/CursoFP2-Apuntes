package edu.dam.pm.yatamap.views.main.tasks;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.dam.pm.yatamap.R;

public class TasksFragment extends Fragment {

    private TasksViewModel mViewModel;

    public static TasksFragment newInstance()
    {
        return new TasksFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }
}