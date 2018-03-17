package example.mmigmur.mmimgurgalleryexample.base;

/**
 * Created by migarcma on 17/3/18.
 */

public class BasePresenterImpl<T>  {
    protected T view;

    public BasePresenterImpl(T baseView) {
        this.view = baseView;
    }

}
