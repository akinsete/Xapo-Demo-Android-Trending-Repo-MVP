package trendingrepo.test.demo.xapo.app.di;

import android.app.Application;

import trendingrepo.test.demo.xapo.app.ui.repodetail.RepoDetailModule;
import trendingrepo.test.demo.xapo.app.ui.repolist.RepoListModule;

public class App extends Application {

    private ApplicationComponent component;


    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .repoListModule(new RepoListModule())
                .repoDetailModule(new RepoDetailModule())
                .build();

    }

    public ApplicationComponent getComponent(){
        return component;
    }

}
