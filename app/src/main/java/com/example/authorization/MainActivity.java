package com.example.authorization;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String previousLogin = sharedPref.getString("previousLogin", null);
        if(sharedPref.getString(previousLogin, null) == null){
            changeFragment(new LoginFragment());
        }
        else{
            changeFragment(new MainFragment());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_login) {
            changeFragment(new LoginFragment());
            Toast.makeText(this, "Login clicked", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.action_register) {
            changeFragment(new RegistrationFragment());
            Toast.makeText(this, "Register clicked", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.action_exit) {
            changeFragment(new LoginFragment());
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("previousLogin", null);
            editor.apply();
            Toast.makeText(this, "Exit clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit();
    }

}