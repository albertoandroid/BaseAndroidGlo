package com.androiddesdecero.jwtudemy;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class BaseActivity extends AppCompatActivity {

    private BaseFragment currentFragment;
    public Environments currentEnviroment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Environments getCurrentEnviroment(){
        if(currentEnviroment != null){
            return currentEnviroment;
        }else {
            currentEnviroment = Environments.DEV;
            return Environments.DEV;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        currentFragment = null;
    }

    protected void showFragment(BaseFragment fragment){
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.homeFragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if(count == 0){
            super.onBackPressed();
            //aditional code
        }else{
            getSupportFragmentManager().popBackStack();
        }
    }
}
