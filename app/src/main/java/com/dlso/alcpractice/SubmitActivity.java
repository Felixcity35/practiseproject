package com.dlso.alcpractice;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.dlso.alcpractice.api.Json;
import com.dlso.alcpractice.model.SkillLeadersModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitActivity extends AppCompatActivity {

    public static final String  TAG  = SubmitActivity.class.getSimpleName() ;
     EditText projectLink;
     EditText Email;
     EditText lastName ;
     EditText firstName ;
     private ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


          firstName = findViewById(R.id.firstname);
          lastName = findViewById(R.id.lastname);
          Email = findViewById(R.id.email);
          projectLink = findViewById(R.id.projectlink);
          Button   submitBtn  = findViewById(R.id.submit_btn);
          progressDialog = new ProgressDialog(this);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialogBtn();
            }
        });

    }

    private void AlertDialogBtn (){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.confirm_layout,null);

        Button yesbtn = view.findViewById(R.id.yes_btn);
        ImageButton cancel_btn = view.findViewById(R.id.cancel_btn);
        builder.setView(view);

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });

        yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 String fName = firstName.getText().toString() ;
                 String email = Email.getText().toString() ;
                 String lName = lastName.getText().toString() ;
                 String link = projectLink.getText().toString() ;

                 postDetails(fName,lName,email,link);
                 
            }
        });


        builder.create() ;
        builder.show();

    }

    private void AlertSuccessful (){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.submit_success,null);

        builder.setView(view);
        builder.create() ;
        builder.show();


    }

    private void AlertNotSuccessful (){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.submit_not_successful,null);
        builder.setView(view);

        builder.create() ;
        builder.show();

    }

    private void postDetails(String fname, String lname, String email, String link) {

        progressDialog.setMessage("submitting....");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Json.DOC_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Json api = retrofit.create(Json.class);
        Call<ResponseBody> submitRequest = api.sendPost(fname, lname, email, link);
        submitRequest.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() || response.code() == 200) {

                    assert response.body() != null;
                    try {
                        Log.e(TAG, "" + response.body().string());
                        Toast.makeText(SubmitActivity.this, "code : "+response.code(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG, "" + response.code());
                    AlertSuccessful();


                } else {
                    progressDialog.dismiss();
                    AlertNotSuccessful();

                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Log.e(TAG, t.getMessage());
            }
        });

    }

}
