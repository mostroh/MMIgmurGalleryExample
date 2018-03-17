package example.mmigmur.domain.di;


import dagger.Subcomponent;
import example.mmigmur.domain.di.scopes.PerInteractor;
import example.mmigmur.domain.interactors.LoginInteractorImpl;


@PerInteractor
@Subcomponent(
        modules = {
                BaseInteractorModule.class
        })
public interface BaseInteractorComponent {

    void inject(LoginInteractorImpl loginInteractor);
}
