package example.mmigmur.domain.interactors;

import javax.inject.Inject;

import example.mmigmur.domain.boundaries.AuthStorageRepoInterface;
import example.mmigmur.domain.boundaries.LoginRepoInterface;
import example.mmigmur.domain.di.BaseInteractorComponent;
import example.mmigmur.domain.model.Authorization;

/**
 * Created by migarcma on 17/3/18.
 */

public class LoginInteractorImpl implements LoginInteractor {

    @Inject
    LoginRepoInterface loginRepoInterface;

    @Inject
    AuthStorageRepoInterface authStorageRepoInterface;

    public LoginInteractorImpl(BaseInteractorComponent baseInteractorComponent) {
        baseInteractorComponent.inject(this);
    }

    @Override
    public void login(Integer clientId, String clientSecret,LoginInteractorResponse loginInteractorResponse) {

        LoginRunnable loginRunnable = new LoginRunnable(clientId,clientSecret,loginInteractorResponse);
    }

    private class LoginRunnable implements Runnable, LoginRepoInterface.LoginResponseListener {

        private LoginInteractorResponse loginInteractorResponse;
        private Integer clientId;
        private String clientSecret;

        private LoginRunnable(Integer clientId, String clientSecret,LoginInteractorResponse loginInteractorResponse) {

            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.loginInteractorResponse = loginInteractorResponse;
        }

        @Override
        public void run() {

            String refreshToken = authStorageRepoInterface.getRefreshToken();
            loginRepoInterface.login(refreshToken,clientId,clientSecret,this);
        }

        @Override
        public void onLoginSuccesResponse(Authorization authorization) {

            if (authorization !=null){
                String accessToken = authorization.getAccessToken();
                String refreshtoken = authorization.getRefreshToken();
                Integer accountId = authorization.getAccountId();
                String username = authorization.getUsername();
                authStorageRepoInterface.saveCredentials(accessToken,refreshtoken,username,accountId);
                loginInteractorResponse.onLoginInteractorResponseSuccess(authorization);
            }
        }
    }
}
