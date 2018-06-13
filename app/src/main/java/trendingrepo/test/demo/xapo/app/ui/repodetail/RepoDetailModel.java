package trendingrepo.test.demo.xapo.app.ui.repodetail;

import rx.Observable;
import trendingrepo.test.demo.xapo.app.data.github.GithubApi;
import trendingrepo.test.demo.xapo.app.data.models.Repo;

public class RepoDetailModel implements RepoDetailMVP.Model {

    private GithubApi githubApi;

    public RepoDetailModel(GithubApi githubApi) {
        this.githubApi = githubApi;
    }

    @Override
    public Observable<Repo> getRepo(int repoId) {
        return githubApi.getRepo(repoId);
    }
}
