package com.dlso.alcpractice.api;

import com.dlso.alcpractice.model.LearningLeadersModel;
import com.dlso.alcpractice.model.SkillLeadersModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Json {


    String BASE_URL = "https://gadsapi.herokuapp.com/";
    String DOC_BASE_URL = "https://docs.google.com/forms/d/e/";

    @GET("api/hours")
    Call<List<LearningLeadersModel>> getLearnersDetails();


    @GET("api/skilliq")
    Call<List<SkillLeadersModel>> getSkillLeadersDetails();

    // formresponse is always added to the end instead of viewform


    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
     Call<ResponseBody> sendPost(@Field("entry.1877115667") String name ,
                                 @Field("entry.2006916086") String LastName ,
                                 @Field("entry.1824927963") String email,
                                 @Field("entry.284483984") String link);




}
