package example.mmigmur.domain.interactors;

import example.mmigmur.domain.model.Authorization;

/**
 * Created by migarcma on 17/3/18.
 */

public interface LoginInteractor {

    void login(Integer clientId, String clientSecret, LoginInteractorResponse loginInteractorResponse);

    interface LoginInteractorResponse {

        void onLoginInteractorResponseSuccess(Authorization authorization);
    }
}
