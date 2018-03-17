package example.mmigmur.mmimgurgalleryexample;
import android.app.Application;


import javax.inject.Inject;

import example.mmigmur.mmimgurgalleryexample.di.application.AppComponent;
import example.mmigmur.mmimgurgalleryexample.di.application.AppModule;
import example.mmigmur.mmimgurgalleryexample.di.network.NetModule;
import example.mmigmur.mmimgurgalleryexample.utils.Constants;
import retrofit2.Retrofit;

public class MMImgurGalleryApp extends Application {

    private AppComponent mMiMovistarAppComponent;
    private static MMImgurGalleryApp app;
    @Inject
    Retrofit retrofit;

    private boolean shouldShowPrepaidBanner = true;
    private boolean reloadLandingConsumption = false;
    private boolean reloadDetailsConsumption = false;

    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     * Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.
     * If you override this method, be sure to call super.onCreate().
     */
    @Override
    public void onCreate() {
        super.onCreate();
       // Fabric.with(this, new Crashlytics());

        initDI();
        app=this;
    }

    public void initDI() {

        mMiMovistarAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(Constants.BASE_URL, this)).build();

        // Inject App inside DI Graph
        mMiMovistarAppComponent.inject(this);
    }

    public AppComponent getComponent() {
        return app.mMiMovistarAppComponent;
    }

    public boolean isReloadLandingConsumption() {
        return reloadLandingConsumption;
    }

    public void setReloadLandingConsumption(boolean reloadLandingConsumption) {
        this.reloadLandingConsumption = reloadLandingConsumption;
    }

    public boolean isReloadDetailsConsumption() {
        return reloadDetailsConsumption;
    }

    public void setReloadDetailsConsumption(boolean reloadDetailsConsumption) {
        this.reloadDetailsConsumption = reloadDetailsConsumption;
    }
}
