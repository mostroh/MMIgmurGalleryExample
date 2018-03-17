package example.mmigmur.data.storage;

import android.content.Context;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import example.mmigmur.domain.boundaries.AuthStorageRepoInterface;

/**
 * Created by migarcma on 17/3/18.
 */

public class AuthStorage extends LocalStorageBase implements AuthStorageRepoInterface {

    private final String accessTokenKey = "accessToken";
    private final String refreshTokenKey = "refreshToken";
    private final String usernameKey = "username";
    private final String accountIdKey = "accountId";

    private Gson gson;

    public AuthStorage(Context context, Gson gson) {
        super(context);
        this.gson = gson;
    }

    @Override
    public void saveCredentials(String accessToken, String refreshToken, String username, Integer accountId) {
        Map<String, String> params = new HashMap<>();
        params.put(accessTokenKey, accessToken);
        params.put(refreshTokenKey, refreshToken);
        params.put(usernameKey, username);
        params.put(accountIdKey, String.valueOf(accountId));
        saveData(params);
    }

    @Override
    public String getAccessToken() {
        return loadData(accessTokenKey);
    }

    @Override
    public String getRefreshToken() {
        return loadData(refreshTokenKey);
    }

    @Override
    public String getUsername() {
        return loadData(usernameKey);
    }

    @Override
    public String getAccountId() {
        return loadData(accountIdKey);
    }
}
