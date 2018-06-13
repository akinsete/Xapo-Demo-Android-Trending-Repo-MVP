package trendingrepo.test.demo.xapo.app.ui.repolist;

import android.util.Log;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import trendingrepo.test.demo.xapo.app.data.models.GithubSearchResponse;
import trendingrepo.test.demo.xapo.app.data.models.Repo;

public class RepoListActivityPresenter implements RepoListMVP.Presenter{


    final static String TAG = RepoListActivityPresenter.class.getSimpleName();

    private RepoListMVP.Model model;
    private RepoListMVP.View view;
    private Subscription subscription;


    public RepoListActivityPresenter(RepoListMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(RepoListMVP.View view) {
        this.view = view;
    }

    @Override
    public void getRepositories(int page) {
        subscription = model.getRepositories(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GithubSearchResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG,"Completed");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,e.getMessage());
                    }

                    @Override
                    public void onNext(GithubSearchResponse githubSearchResponse) {
                        List<Repo> repos = githubSearchResponse.getItems();
                        if(view != null){
                            view.displayRepository(repos);
                        }
                    }
                });
    }

    @Override
    public void rxUnSubScribe() {
        if(subscription != null){
            if(!subscription.isUnsubscribed()){
                subscription.unsubscribe();
            }
        }
    }
}
