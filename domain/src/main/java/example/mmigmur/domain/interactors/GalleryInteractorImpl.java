package example.mmigmur.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import example.mmigmur.domain.boundaries.AuthStorageRepoInterface;
import example.mmigmur.domain.boundaries.GalleryRepoInterface;
import example.mmigmur.domain.di.BaseInteractorComponent;
import example.mmigmur.domain.model.Image;

/**
 * Created by migarcma on 18/3/18.
 */

public class GalleryInteractorImpl implements GalleryInteractor {

    @Inject
    GalleryRepoInterface galleryRepoInterface;

    @Inject
    AuthStorageRepoInterface authStorageRepoInterface;

    public GalleryInteractorImpl(BaseInteractorComponent baseInteractorComponent) {
        baseInteractorComponent.inject(this);
    }

    @Override
    public void getAccountGallery(AccountGalleryInteractorResponse accountGalleryInteractorResponse) {
        AccountGalleryRunnable accountGalleryRunnable = new AccountGalleryRunnable(accountGalleryInteractorResponse);
        accountGalleryRunnable.run();
    }

    private class AccountGalleryRunnable implements Runnable, GalleryRepoInterface.GalleryResponseListener {

        private AccountGalleryInteractorResponse accountGalleryInteractorResponse;

        private AccountGalleryRunnable(AccountGalleryInteractorResponse accountGalleryInteractorResponse) {
            this.accountGalleryInteractorResponse = accountGalleryInteractorResponse;
        }

        @Override
        public void run() {

            String refreshToken = authStorageRepoInterface.getRefreshToken();
            String accessToken = authStorageRepoInterface.getAccessToken();
            galleryRepoInterface.getAccountGallery(refreshToken, accessToken,this);
        }

        @Override
        public void onAccountGallerySuccesResponse(List<Image> gallery) {
            accountGalleryInteractorResponse.onAccountGallerySuccess(gallery);
        }

        @Override
        public void onAccountGalleryFailureConnection() {
            accountGalleryInteractorResponse.onAccountGalleryConnectionFailure();
        }

        @Override
        public void onAccountGalleryFailure() {
            accountGalleryInteractorResponse.onAccountGalleryFailure();
        }
    }
}
