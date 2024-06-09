package com.example.authorization.service;

import android.content.SharedPreferences;

import com.example.authorization.MainFragment;
import com.example.authorization.R;

public class AuthorizationService {

    private AuthorizationService(){}

    private static AuthorizationService instance;

    public static AuthorizationService getInstance(){
        if (instance == null){
            instance = new AuthorizationService();
        }
        return instance;
    }
    public void setPreviousLogin(SharedPreferences.Editor editor, String login){
        editor.putString("previousLogin", login);
        editor.apply();
    }

    public void setNewUser(SharedPreferences.Editor editor,String login, String password){
        setPreviousLogin(editor, login);
        editor.putString(login, password);
        editor.apply();
    }
}
