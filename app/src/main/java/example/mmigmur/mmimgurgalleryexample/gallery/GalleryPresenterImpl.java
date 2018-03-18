package example.mmigmur.mmimgurgalleryexample.gallery;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import example.mmigmur.domain.interactors.GalleryInteractor;
import example.mmigmur.domain.model.Image;
import example.mmigmur.mmimgurgalleryexample.base.BasePresenterImpl;
import example.mmigmur.mmimgurgalleryexample.di.presenter.BasePresenterComponent;
import example.mmigmur.mmimgurgalleryexample.viewmodel.ImageViewModel;
import example.mmigmur.mmimgurgalleryexample.viewmodel.mapper.ImageViewModelToImageMapper;

/**
 * Created by migarcma on 17/3/18.
 */

public class GalleryPresenterImpl extends BasePresenterImpl<GalleryView> implements GalleryPresenter,
        GalleryInteractor.AccountGalleryInteractorResponse {

    @Inject
    GalleryInteractor galleryInteractor;

    public GalleryPresenterImpl(BasePresenterComponent basePresenterComponent, GalleryView view) {
        super(view);
        basePresenterComponent.injectPresenter(this);
    }

    @Override
    public void init() {
        view.initViews();
        view.setUpGallery();
        refreshGallery();
    }

    @Override
    public void onAccountGallerySuccess(List<Image> gallery) {
        List<ImageViewModel> viewGallery = new ArrayList<>();
        if (gallery!=null && !gallery.isEmpty()){
            ImageViewModelToImageMapper imageViewModelToImageMapper = new ImageViewModelToImageMapper();
            for (Image image: gallery){
                ImageViewModel imageViewModel = imageViewModelToImageMapper.reverseMap(image);
                viewGallery.add(imageViewModel);
            }
        }
        view.hideLoading();
        view.refreshGallery(viewGallery);
    }

    @Override
    public void onAccountGalleryFailure() {
        view.hideLoading();
        view.showGalleryError();
    }

    @Override
    public void onAccountGalleryConnectionFailure() {
        view.hideLoading();
        view.showConnectionError();
    }

    @Override
    public void refreshGallery() {
        view.showLoading();
        galleryInteractor.getAccountGallery(this);
    }
}
