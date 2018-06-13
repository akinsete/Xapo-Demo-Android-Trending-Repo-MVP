package trendingrepo.test.demo.xapo.app.ui.repolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import trendingrepo.test.demo.xapo.app.R;
import trendingrepo.test.demo.xapo.app.data.models.Repo;
import trendingrepo.test.demo.xapo.app.di.App;
import trendingrepo.test.demo.xapo.app.ui.BaseActivity;
import trendingrepo.test.demo.xapo.app.ui.repodetail.RepoDetailsActivity;
import trendingrepo.test.demo.xapo.app.utils.Constants;

public class RepoListActivity extends BaseActivity implements RepoListMVP.View {


    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.loading_more) TextView loading_more;

    @Inject
    RepoListMVP.Presenter presenter;


    LinearLayoutManager layoutManager;
    RepoListAdapter repoListAdapter;
    int currentPageNumber = 1;
    List<Repo> repos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ((App) getApplication()).getComponent().inject(this);

        getSupportActionBar().setTitle(R.string.main_title);

        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.setView(this);

        loadMoreContent();
    }

    private void loadMoreContent() {
        presenter.getRepositories(currentPageNumber);
    }

    private void setupRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        repoListAdapter = new RepoListAdapter(repos);
        repoListAdapter.setRepoListAdapterListener(new RepoListAdapter.RepoListAdapterListener() {
            @Override
            public void onItemClicked(Repo repo) {
                Intent intent = new Intent(RepoListActivity.this,RepoDetailsActivity.class);
                intent.putExtra(Constants.REPO_ID,repo.getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(repoListAdapter);
        setupRecyclerListener();
    }


    @Override
    public void displayRepository(List<Repo> repos) {
        this.repos.addAll(repos);
        repoListAdapter.setRepos(this.repos);
        loading_more.setVisibility(View.GONE);
    }


    private void setupRecyclerListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                if(totalItemCount == layoutManager.findLastVisibleItemPosition() + 1){
                    currentPageNumber++;
                    loading_more.setVisibility(View.VISIBLE);
                    loadMoreContent();
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.rxUnSubScribe();
    }
}
