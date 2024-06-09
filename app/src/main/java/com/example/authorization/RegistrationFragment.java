package com.example.authorization;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.authorization.service.AuthorizationService;

import java.util.Objects;

public class RegistrationFragment extends Fragment {

    private EditText login;

    private EditText password;

    private EditText confirmPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        login = view.findViewById(R.id.editTextLogin);
        password = view.findViewById(R.id.editTextPassword);
        confirmPassword = view.findViewById(R.id.editTextConfirmPassword);

        view.findViewById(R.id.buttonSignUp).setOnClickListener(x->{
            String login = this.login.getText().toString();
            String password = this.password.getText().toString();
            String confirmPassword = this.confirmPassword.getText().toString();
            if(MainActivity.sharedPref.getString(login, null) != null){
                Toast.makeText(getContext(), "User already exists",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if(!password.equals(confirmPassword)){
                Toast.makeText(getContext(), "Passwords do not match",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            AuthorizationService.getInstance()
                    .setNewUser(MainActivity.sharedPref.edit(), login, password);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, new MainFragment())
                    .commit();
        });
        return view;
    }
}
