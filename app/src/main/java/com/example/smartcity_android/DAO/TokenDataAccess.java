package com.example.smartcity_android.DAO;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.smartcity_android.R;
import com.example.smartcity_android.data.RetrofitFactory;
import com.example.smartcity_android.data.model.DTO.Login;
import com.example.smartcity_android.data.model.DTO.TokenDTO;
import com.example.smartcity_android.data.model.Token;
import com.example.smartcity_android.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

// Repo
public class TokenDataAccess implements IToken {
    private Context context;
    public TokenDataAccess(Context context){
        this.context = context;
    }

    @Override
    public void findLogin(Login login) {
        Retrofit retrofit = RetrofitFactory.getIntanceWithoutToken();
        LoginService loginService = retrofit.create(LoginService.class);
        Call<TokenDTO> call = loginService.findLoginRetro(new Login(login.getEmail(),login.getPassword()));
        call.enqueue(new Callback<TokenDTO>() {
            @Override
            public void onResponse(Call<TokenDTO> call, Response<TokenDTO> response) {
                if (!response.isSuccessful()) {
                    Log.i("student", "no succes");
                    return;
                }
                TokenDTO res = response.body();
                RetrofitFactory.setToken(new Token(res.accessToken, res.expiresIn));
                JWT jwt = new JWT(res.accessToken);
                Claim claim = jwt.getClaim("UserId");
                int id = claim.asInt();
                SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_pref_student), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(context.getString(R.string.id_current_student), id);
                editor.apply();
            }

            @Override
            public void onFailure(Call<TokenDTO> call, Throwable t) {
                Log.i("student", "error " + t.getMessage());
            }
        });
    }
}
