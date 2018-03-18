package example.mmigmur.data.services;


import android.util.Log;

import javax.inject.Inject;

import example.mmigmur.data.di.BaseRepositoryComponent;
import example.mmigmur.data.entities.AuthorizationEntity;
import example.mmigmur.data.entities.mapper.AuthorizationToAuthorizationEntityMapper;
import example.mmigmur.data.network.ImgurApiInterface;
import example.mmigmur.domain.boundaries.LoginRepoInterface;
import example.mmigmur.domain.model.Authorization;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by migarcma on 17/3/18.
 */

public class LoginService implements LoginRepoInterface {

    @Inject
    ImgurApiInterface imgurApiInterface;

    public LoginService(BaseRepositoryComponent baseRepositoryComponent) {
        baseRepositoryComponent.inject(this);
    }

    @Override
    public void login(String refreshToken, String clientId, String clientSecret, final LoginResponseListener loginResponseListener) {
        imgurApiInterface.login(refreshToken,
                    clientId,
                    clientSecret,
                    "refresh_token")
                .enqueue(new Callback<AuthorizationEntity>() {
                    @Override
                    public void onResponse(Call<AuthorizationEntity> call, Response<AuthorizationEntity> response) {
                        if (response.isSuccessful()){
                            AuthorizationToAuthorizationEntityMapper authorizationToAuthorizationEntityMapper = new AuthorizationToAuthorizationEntityMapper();
                            Authorization authorization = authorizationToAuthorizationEntityMapper.reverseMap(response.body());
                            loginResponseListener.onLoginSuccesResponse(authorization);
                        } else {
                            loginResponseListener.onLoginFailureAuth();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthorizationEntity> call, Throwable t) {
                        loginResponseListener.onLoginFailureConnection();
                    }
                });
    }
}
