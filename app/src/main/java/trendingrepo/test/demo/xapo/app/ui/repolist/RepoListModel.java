package trendingrepo.test.demo.xapo.app.ui.repolist;

import rx.Observable;
import trendingrepo.test.demo.xapo.app.data.github.GithubApi;
import trendingrepo.test.demo.xapo.app.data.models.GithubSearchResponse;

public class RepoListModel implements RepoListMVP.Model {


    private GithubApi githubApi;

    public RepoListModel(GithubApi githubApi) {
        this.githubApi = githubApi;
    }

    @Override
    public Observable<GithubSearchResponse> getRepositories(int page) {
        return githubApi.getRepositories(page);
    }
}
