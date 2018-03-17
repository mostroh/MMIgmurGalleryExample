package example.mmigmur.domain.boundaries;

/**
 * Created by migarcma on 17/3/18.
 */

public interface AuthStorageRepoInterface extends BaseStorageRepoInterface {

    void saveCredentials(String accessToken, String refreshToken, String username, Integer accountId);

    String getAccessToken();

    String getRefreshToken();

    String getUsername();

    String getAccountId();
}
