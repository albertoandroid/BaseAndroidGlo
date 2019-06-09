package com.androiddesdecero.jwtudemy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.androiddesdecero.jwtudemy.BaseActivity;
import com.androiddesdecero.jwtudemy.R;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        showFragment(HomeFragment.newInstance());
    }
}
