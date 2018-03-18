package example.mmigmur.mmimgurgalleryexample.gallery;

import example.mmigmur.mmimgurgalleryexample.base.BasePresenterImpl;
import example.mmigmur.mmimgurgalleryexample.di.presenter.BasePresenterComponent;

/**
 * Created by migarcma on 18/3/18.
 */

public class UploadPresenterImpl extends BasePresenterImpl<ImageUploadView> implements UploadPresenter {


    public UploadPresenterImpl(BasePresenterComponent basePresenterComponent, ImageUploadView baseView) {
        super(baseView);
        basePresenterComponent.injectPresenter(this);
    }

    @Override
    public void init() {

    }
}
