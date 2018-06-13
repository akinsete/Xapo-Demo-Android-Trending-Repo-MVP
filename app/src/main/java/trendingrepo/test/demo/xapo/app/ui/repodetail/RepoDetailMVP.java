package trendingrepo.test.demo.xapo.app.ui.repodetail;

import rx.Observable;
import trendingrepo.test.demo.xapo.app.data.models.Repo;

public interface RepoDetailMVP {

    interface View {

        void displayRepoDetail(Repo repo);

    }


    interface Presenter {

        void setView(RepoDetailMVP.View view);

        void getRepoDetail(int repo_id);

        void rxUnsubscribe();

    }


    interface Model {

        Observable<Repo> getRepo(int repoId);

    }

}
