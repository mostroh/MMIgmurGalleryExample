package example.mmigmur.mmimgurgalleryexample.gallery;

import javax.inject.Inject;

import example.mmigmur.domain.interactors.GalleryInteractor;
import example.mmigmur.mmimgurgalleryexample.base.BasePresenterImpl;
import example.mmigmur.mmimgurgalleryexample.di.presenter.BasePresenterComponent;

/**
 * Created by migarcma on 18/3/18.
 */

public class ImageDetailPresenterImpl extends BasePresenterImpl<ImageDetailView> implements ImageDetailPresenter, GalleryInteractor.ImageDeleteInteractorResponse {

    @Inject
    GalleryInteractor galleryInteractor;

    public ImageDetailPresenterImpl(BasePresenterComponent basePresenterComponent, ImageDetailView baseView) {
        super(baseView);
        basePresenterComponent.injectPresenter(this);
    }

    @Override
    public void init() {
        view.initViews();
    }

    @Override
    public void deleteImage(String deleteHash) {
        view.showLoading();
        galleryInteractor.deleteImage(deleteHash, this);
    }

    @Override
    public void onDeleteSuccess() {
        view.hideLoading();
        view.notifyImageDeleted();
    }

    @Override
    public void onDeleteFailure() {
        view.hideLoading();
        view.showErrorDeleting();
    }
}
