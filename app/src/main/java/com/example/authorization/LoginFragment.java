package com.example.authorization;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.authorization.service.AuthorizationService;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private EditText login;

    private EditText password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        login = view.findViewById(R.id.editTextLogin);
        password = view.findViewById(R.id.editTextPassword);

        view.findViewById(R.id.buttonSignUp).setOnClickListener(x->{
            String login = this.login.getText().toString();
            String password = this.password.getText().toString();
            if(MainActivity.sharedPref.getString(login,null) == null ||
                    !Objects.equals(MainActivity.sharedPref.getString(login, null), password)){
                Toast.makeText(getContext(), "Please enter correct login or password",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            AuthorizationService.getInstance()
                    .setPreviousLogin(MainActivity.sharedPref.edit(), login);
            requireActivity().getSupportFragmentManager().beginTransaction()
                   .replace(R.id.fragmentContainerView, new MainFragment())
                   .commit();
            Toast.makeText(getContext(), login + " " + password, Toast.LENGTH_SHORT).show();
        });
        return view;
    }
}
