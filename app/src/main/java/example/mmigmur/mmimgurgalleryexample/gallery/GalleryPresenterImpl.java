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
        galleryInteractor.getAccountGallery(this);
        view.showLoading();
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
        view.setUpGallery(viewGallery);
        view.initViews();
    }

    @Override
    public void onAccountGalleryFailure() {
        view.hideLoading();
        view.initViews();
        view.showGalleryError();
    }

    @Override
    public void onAccountGalleryConnectionFailure() {
        view.hideLoading();
        view.initViews();
        view.showConnectionError();
    }
}
