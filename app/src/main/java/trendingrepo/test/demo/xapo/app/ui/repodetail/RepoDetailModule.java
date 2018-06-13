package trendingrepo.test.demo.xapo.app.ui.repodetail;

import dagger.Module;
import dagger.Provides;
import trendingrepo.test.demo.xapo.app.data.github.GithubApi;

/**
 * Created by sundayakinsete on 13/06/2018.
 */


@Module
public class RepoDetailModule {

    @Provides
    public RepoDetailMVP.Model provideRepoDetailModel(GithubApi githubApi){
        return new RepoDetailModel(githubApi);
    }

    @Provides RepoDetailMVP.Presenter provideRepoDetailActivityPresenter(RepoDetailMVP.Model model){
        return new RepoDetailActivityPresenter(model);
    }

}
