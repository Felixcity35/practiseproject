package com.dlso.alcpractice.api;

import com.dlso.alcpractice.model.LearningLeadersModel;
import com.dlso.alcpractice.model.SkillLeadersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Json {


    String BASE_URL = "https://gadsapi.herokuapp.com/";

    @GET("api/hours")
    Call<List<LearningLeadersModel>> getLearnersDetails();


    @GET("api/skilliq")
    Call<List<SkillLeadersModel>> getSkillLeadersDetails();
}
