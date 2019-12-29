package com.example.smartcity_android.ui.controller;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_android.R;
import com.example.smartcity_android.data.RetrofitFactory;
import com.example.smartcity_android.tool.Tool;

import static com.example.smartcity_android.R.menu.menu_main;

public class MenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.myInfo:
                if(Tool.hasInternet(MenuActivity.this)) {
                    intent = new Intent(this, ProfilActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MenuActivity.this, R.string.internet, Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.myCriteria:
                if(Tool.hasInternet(MenuActivity.this)) {
                    intent = new Intent(this, CriterionStudentActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MenuActivity.this, R.string.internet, Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.logout:
                RetrofitFactory.setToken(null);
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
