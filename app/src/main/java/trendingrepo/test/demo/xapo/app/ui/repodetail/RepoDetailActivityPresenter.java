package trendingrepo.test.demo.xapo.app.ui.repodetail;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import trendingrepo.test.demo.xapo.app.data.models.Repo;

public class RepoDetailActivityPresenter implements RepoDetailMVP.Presenter {

    private RepoDetailMVP.Model model;
    private RepoDetailMVP.View view;
    private Subscription subscription;


    public RepoDetailActivityPresenter(RepoDetailMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(RepoDetailMVP.View view) {
        this.view = view;
    }

    @Override
    public void getRepoDetail(int repo_id) {
        model.getRepo(repo_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Repo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Repo repo) {
                        if(view != null){
                            view.displayRepoDetail(repo);
                        }
                    }
                });
    }

    @Override
    public void rxUnsubscribe() {
        if(subscription != null){
            if(!subscription.isUnsubscribed()){
                subscription.unsubscribe();
            }
        }
    }
}
