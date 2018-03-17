package example.mmigmur.mmimgurgalleryexample.gallery;

import example.mmigmur.mmimgurgalleryexample.base.BasePresenterImpl;
import example.mmigmur.mmimgurgalleryexample.di.presenter.BasePresenterComponent;

/**
 * Created by migarcma on 17/3/18.
 */

public class GalleryPresenterImpl extends BasePresenterImpl<GalleryView> implements GalleryPresenter {

    public GalleryPresenterImpl(BasePresenterComponent basePresenterComponent, GalleryView view) {
        super(view);
        basePresenterComponent.injectPresenter(this);
    }

    @Override
    public void init() {
        view.initViews();
    }
}
