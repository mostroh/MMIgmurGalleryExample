package example.mmigmur.mmimgurgalleryexample.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.mmigmur.mmimgurgalleryexample.di.fragment.BaseFragmentComponent;

public abstract class BaseFragment extends Fragment implements BaseFragmentView {

    protected BaseFragmentComponent fragmentComponent;
    protected View view;
    protected Unbinder unbinder;
    protected boolean consummesBack;
    private boolean recreated;


    protected abstract int getXMLToInflate();

    protected abstract void initPresenter();

    public abstract void onBackPressed();

    protected abstract void setConsummesBack();

    public boolean getConsummesBack() {
        return consummesBack;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDIComponent();
        setRetainInstance(true);
    }

    public BaseFragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }

    protected abstract void initDIComponent();

    /**
     * Gets a component for dependency injection by its type.
     */
    protected <C> C getParentComponent(Class<C> componentType) {
        return componentType.cast(((BaseActivity) getActivity()).getActivityComponent());
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (recreated)
            initPresenter();
        if (onViewCreatedListener != null) {
            onViewCreatedListener.OnViewCreated(view);
        }
        setConsummesBack();
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            recreated = true;
            int layoutId = getXMLToInflate();
            if (layoutId > 0) {
                view = inflater.inflate(layoutId, container, false);
            }
            if (view != null) {
                unbinder = ButterKnife.bind(this, view);

            } else {
                view = super.onCreateView(inflater, container, savedInstanceState);
            }

        } else {
            recreated = false;
        }
        if (view != null) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        }
        return view;
    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (view != null) {
            view = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public OnViewCreatedListener getOnViewCreatedListener() {
        return onViewCreatedListener;
    }

    public void setOnViewCreatedListener(OnViewCreatedListener onViewCreatedListener) {
        this.onViewCreatedListener = onViewCreatedListener;
    }


    public interface OnViewCreatedListener {
        void OnViewCreated(View v);
    }

    private OnViewCreatedListener onViewCreatedListener;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }

}
