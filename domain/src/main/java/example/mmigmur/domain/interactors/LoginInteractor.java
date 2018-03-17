package example.mmigmur.domain.interactors;

import example.mmigmur.domain.model.Authorization;

/**
 * Created by migarcma on 17/3/18.
 */

public interface LoginInteractor {

    void login(String clientId, String clientSecret, LoginInteractorResponse loginInteractorResponse);

    void saveCredentials(String refreshToken, String accessToken, String userName);

    interface LoginInteractorResponse {

        void onLoginInteractorResponseSuccess(Authorization authorization);

        void onLoginInteractorResponseFailureConnection();

        void onLoginInteractorResponseFailureAuth();
    }
}
