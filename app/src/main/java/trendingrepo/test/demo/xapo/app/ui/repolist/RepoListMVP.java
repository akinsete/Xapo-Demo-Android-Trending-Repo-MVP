package trendingrepo.test.demo.xapo.app.ui.repolist;

import java.util.List;

import rx.Observable;
import trendingrepo.test.demo.xapo.app.data.models.GithubSearchResponse;
import trendingrepo.test.demo.xapo.app.data.models.Repo;

public interface RepoListMVP {

    interface View {

        void displayRepository(List<Repo> repos);

    }


    interface Presenter {

        void setView(RepoListMVP.View view);

        void getRepositories(int page);

        void rxUnSubScribe();

    }


    interface Model {

        Observable<GithubSearchResponse> getRepositories(int page);

    }

}
