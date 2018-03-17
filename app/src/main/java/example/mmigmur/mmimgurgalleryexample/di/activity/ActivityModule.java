package example.mmigmur.mmimgurgalleryexample.di.activity;


import dagger.Module;
import dagger.Provides;
import example.mmigmur.mmimgurgalleryexample.base.BaseActivity;
import example.mmigmur.mmimgurgalleryexample.di.scopes.PerActivity;
import example.mmigmur.mmimgurgalleryexample.login.LoginActivity;


@Module
public class ActivityModule {

  BaseActivity baseActivity;

  public ActivityModule(BaseActivity baseActivity) {
    this.baseActivity = baseActivity;
  }

  @Provides
  @PerActivity
  LoginActivity provideLoginActivity() {
    return (LoginActivity)this.baseActivity;
  }

}
