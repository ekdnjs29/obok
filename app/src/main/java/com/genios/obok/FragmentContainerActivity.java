package com.genios.obok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.provider.MediaStore;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentContainerActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private static final int PICK_IMAGE_REQUEST = 1;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeFragment()).commit();
        bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.btn_home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeFragment()).commit();
                }
                else if (item.getItemId()==R.id.btn_medical) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new MedicalFacilitiesFragment()).commit();
                }
                else if (item.getItemId()==R.id.btn_pet) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new PetDermatologyFragment()).commit();
                }
                else if (item.getItemId()==R.id.btn_alarm) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new NotifyFragment()).commit();
                }
                else if (item.getItemId()==R.id.btn_UserSetting) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new ProfileFragment()).commit();
                }
                return true;
            }
        });
    }

}