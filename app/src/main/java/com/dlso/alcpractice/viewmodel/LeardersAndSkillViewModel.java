package com.dlso.alcpractice.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dlso.alcpractice.api.Json;
import com.dlso.alcpractice.model.LearningLeadersModel;
import com.dlso.alcpractice.model.SkillLeadersModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LeardersAndSkillViewModel extends ViewModel {

    private static final String TAG = LeardersAndSkillViewModel.class.getSimpleName() ;
    private MutableLiveData<List<LearningLeadersModel>> leadersModelMutableLiveData ;
    private MutableLiveData<List<SkillLeadersModel>> skillLeaders ;


    public LiveData<List<LearningLeadersModel>> getLearningLeadersModel(){

        if (leadersModelMutableLiveData == null){
            leadersModelMutableLiveData = new MutableLiveData<>();
            // call method
            getLearningLeader();
        }

        return leadersModelMutableLiveData ;
    }

    public LiveData<List<SkillLeadersModel>> getSkillLeadersModel(){

        if (skillLeaders == null){
            skillLeaders = new MutableLiveData<>();
            // call method
           getSKillLeader();
        }

        return skillLeaders ;
    }

    private void getLearningLeader() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Json.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Json api = retrofit.create(Json.class);
        Call<List<LearningLeadersModel>> callSchedule = api.getLearnersDetails();
        callSchedule.enqueue(new Callback<List<LearningLeadersModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<LearningLeadersModel>> call, @NonNull Response<List<LearningLeadersModel>> response) {
                leadersModelMutableLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(@NonNull Call<List<LearningLeadersModel>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }


    private void getSKillLeader() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Json.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

               Json api = retrofit.create(Json.class);
               Call<List<SkillLeadersModel>> callSchedule = api.getSkillLeadersDetails();
               callSchedule.enqueue(new Callback<List<SkillLeadersModel>>() {
              @Override
             public void onResponse(@NonNull Call<List<SkillLeadersModel>> call, @NonNull Response<List<SkillLeadersModel>> response) {
                skillLeaders.setValue(response.body());
            }
            @Override
            public void onFailure(@NonNull Call<List<SkillLeadersModel>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}
