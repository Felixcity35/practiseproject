package com.dlso.alcpractice.ui.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.dlso.alcpractice.adapter.SkillLeadersAdapter;
import com.dlso.alcpractice.model.LearningLeadersModel;
import com.dlso.alcpractice.model.SkillLeadersModel;
import com.dlso.alcpractice.viewmodel.LeardersAndSkillViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkillIQLeaders extends Fragment {

    private RecyclerView skillsLeadersRecy ;
    private SkillLeadersAdapter skillLeadersAdapter ;
    private LeardersAndSkillViewModel leardersAndSkillViewModel ;


    public SkillIQLeaders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_iqleaders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        skillsLeadersRecy = view.findViewById(R.id.skill_Recy);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        skillsLeadersRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        skillsLeadersRecy.setHasFixedSize(true);
        skillsLeadersRecy.addItemDecoration(new DividerItemDecoration(skillsLeadersRecy.getContext(),DividerItemDecoration.VERTICAL));
        skillLeadersAdapter  = new SkillLeadersAdapter();
        skillsLeadersRecy.setAdapter(skillLeadersAdapter);

        leardersAndSkillViewModel = new ViewModelProvider(this).get(LeardersAndSkillViewModel.class);
        leardersAndSkillViewModel.getSkillLeadersModel().observe(getViewLifecycleOwner(), new Observer<List<SkillLeadersModel>>() {
            @Override
            public void onChanged(List<SkillLeadersModel> skillLeadersModels) {
                skillLeadersAdapter.setSkillLeaders(skillLeadersModels,getContext());
            }
        });
    }
}
