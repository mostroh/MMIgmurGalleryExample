package example.mmigmur.domain.boundaries;

import example.mmigmur.domain.model.Authorization;

/**
 * Created by migarcma on 17/3/18.
 */

public interface LoginRepoInterface {

    void login(String refreshToken,
               Integer clientId,
               String clientSecret,
               LoginResponseListener loginResponseListener);

    interface LoginResponseListener {

        void onLoginSuccesResponse(Authorization authorization);
    }
}
