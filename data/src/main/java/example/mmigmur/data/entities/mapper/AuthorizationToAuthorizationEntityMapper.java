package example.mmigmur.data.entities.mapper;

import example.mmigmur.data.entities.AuthorizationEntity;
import example.mmigmur.domain.model.Authorization;

/**
 * Created by migarcma on 17/3/18.
 */

public class AuthorizationToAuthorizationEntityMapper extends Mapper<Authorization, AuthorizationEntity> {

    public AuthorizationToAuthorizationEntityMapper() {
    }

    @Override
    public AuthorizationEntity map(Authorization value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Authorization reverseMap(AuthorizationEntity value) {
        Authorization authorization = new Authorization();
        authorization.setAccessToken(value.getAccessToken());
        authorization.setRefreshToken(value.getRefreshToken());
        authorization.setAccountId(value.getAccountId());
        authorization.setUsername(value.getUsername());
        return authorization;
    }
}
