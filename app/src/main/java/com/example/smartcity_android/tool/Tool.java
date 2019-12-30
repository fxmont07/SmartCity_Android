package com.example.smartcity_android.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Patterns;

import com.example.smartcity_android.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class Tool {
    // Static class for use tool, static method
    public static boolean isEmailValid(TextInputLayout target, Context context) {
        String input = target.getEditText().getText().toString().trim();
        if(input.isEmpty()) {
            target.setError(context.getText(R.string.fieldEmpty));
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
            target.setError(context.getText(R.string.wrongEmailFormat));
            return false;
        }
        return true;
    }
    public static boolean hasLengthValid(TextInputLayout target, Context context, int minLength) {
        if(target.getEditText().getText().toString().length() < minLength) {
            target.setError(context.getText(R.string.wrongFieldLength) + " " + minLength);
            return false;
        }
        return true;
    }

    public static boolean hasPostCodeValid(TextInputLayout target, Context context){
        Pattern p = Pattern.compile("[1-9]\\d{3}");
        if(!p.matcher(target.getEditText().getText().toString()).matches()){
            target.setError(context.getText(R.string.wrongPostCode));
            return false;
        }
        return true;
    }

    public static boolean hasInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}