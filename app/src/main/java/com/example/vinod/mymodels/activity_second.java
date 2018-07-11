package com.example.vinod.mymodels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class activity_second extends AppCompatActivity {

    @BindView(R.id.btn_logout)
    Button btnLogout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        mAuth=FirebaseAuth.getInstance();


    }

    @OnClick(R.id.btn_logout)
    public void onViewClicked() {
        mAuth.signOut();
        LoginManager.getInstance().logOut();
    }
}
