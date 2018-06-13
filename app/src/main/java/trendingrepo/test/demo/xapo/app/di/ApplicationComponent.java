package trendingrepo.test.demo.xapo.app.di;


import javax.inject.Singleton;

import dagger.Component;
import trendingrepo.test.demo.xapo.app.data.ApiModule;
import trendingrepo.test.demo.xapo.app.ui.repodetail.RepoDetailModule;
import trendingrepo.test.demo.xapo.app.ui.repodetail.RepoDetailsActivity;
import trendingrepo.test.demo.xapo.app.ui.repolist.RepoListActivity;
import trendingrepo.test.demo.xapo.app.ui.repolist.RepoListModule;

@Singleton
@Component(modules = {ApplicationModule.class, RepoListModule.class, ApiModule.class, RepoDetailModule.class})
public interface ApplicationComponent {

    void inject (RepoListActivity target);

    void inject (RepoDetailsActivity target);

}