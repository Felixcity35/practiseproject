package com.dlso.alcpractice.ui.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dlso.alcpractice.R;
import com.dlso.alcpractice.adapter.LearningLeadersAdapter;
import com.dlso.alcpractice.model.LearningLeadersModel;
import com.dlso.alcpractice.viewmodel.LeardersAndSkillViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearningLeaders extends Fragment {

    private RecyclerView LearningLeadersRecy ;
    private  LearningLeadersAdapter learningLeadersAdapter ;
    private LeardersAndSkillViewModel leardersAndSkillViewModel ;


    public LearningLeaders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning_leaders, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LearningLeadersRecy = view.findViewById(R.id.LearningLeadersRecy);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LearningLeadersRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        LearningLeadersRecy.setHasFixedSize(true);
        LearningLeadersRecy.addItemDecoration(new DividerItemDecoration(LearningLeadersRecy.getContext(),DividerItemDecoration.VERTICAL));
        learningLeadersAdapter  = new LearningLeadersAdapter();
        LearningLeadersRecy.setAdapter(learningLeadersAdapter);

        leardersAndSkillViewModel = new ViewModelProvider(this).get(LeardersAndSkillViewModel.class);
        leardersAndSkillViewModel.getLearningLeadersModel().observe(getViewLifecycleOwner(), new Observer<List<LearningLeadersModel>>() {
            @Override
            public void onChanged(List<LearningLeadersModel> learningLeadersModels) {
                learningLeadersAdapter.setLearningLeaders(learningLeadersModels,getContext());
            }
        });
    }
}
