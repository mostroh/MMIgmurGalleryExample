package example.mmigmur.domain.interactors;

import java.util.List;

import example.mmigmur.domain.model.Image;

/**
 * Created by migarcma on 18/3/18.
 */

public interface GalleryInteractor {

    void getAccountGallery(AccountGalleryInteractorResponse galleryInteractorAccountGalleryResponse);

    interface AccountGalleryInteractorResponse {

        void onAccountGallerySuccess(List<Image> gallery);

        void onAccountGalleryFailure();

        void onAccountGalleryConnectionFailure();
    }
}
