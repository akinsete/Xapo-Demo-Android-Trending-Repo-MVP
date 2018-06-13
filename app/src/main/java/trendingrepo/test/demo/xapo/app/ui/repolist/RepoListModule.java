package trendingrepo.test.demo.xapo.app.ui.repolist;


import dagger.Module;
import dagger.Provides;
import trendingrepo.test.demo.xapo.app.data.github.GithubApi;

/**
 * Created by sundayakinsete on 13/06/2018.
 */


@Module
public class RepoListModule {

    @Provides
    public RepoListMVP.Presenter provideRepoListActivityPresenter(RepoListMVP.Model model){
        return new RepoListActivityPresenter(model);
    }


    @Provides
    public RepoListMVP.Model provideRepoListModel(GithubApi githubApi){
        return new RepoListModel(githubApi);
    }

}
