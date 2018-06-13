package trendingrepo.test.demo.xapo.app.ui.repodetail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import trendingrepo.test.demo.xapo.app.R;
import trendingrepo.test.demo.xapo.app.data.models.Repo;
import trendingrepo.test.demo.xapo.app.di.App;
import trendingrepo.test.demo.xapo.app.ui.BaseActivity;
import trendingrepo.test.demo.xapo.app.utils.Constants;

public class RepoDetailsActivity extends BaseActivity implements RepoDetailMVP.View {

    @BindView(R.id.repo_title) TextView repo_title;
    @BindView(R.id.repo_description) TextView repo_description;
    @BindView(R.id.repo_full_description) TextView repo_full_description;
    @BindView(R.id.repo_watchers) TextView repo_watchers;
    @BindView(R.id.repo_stars) TextView repo_stars;
    @BindView(R.id.repo_forks) TextView repo_forks;
    @BindView(R.id.repo_open_issues) TextView repo_open_issues;
    @BindView(R.id.repo_language) TextView repo_language;
    @BindView(R.id.owner_avatar) CircleImageView ownerAvatar;

    int repoId;
    private Repo repo;

    @Inject
    RepoDetailMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);

        ButterKnife.bind(this);

        ((App) getApplication()).getComponent().inject(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handleIntent(getIntent());

        presenter.setView(this);

        getRepoDetails();
    }


    private void getRepoDetails() {
        showLoading("Please wait");
        presenter.getRepoDetail(repoId);
    }

    private void handleIntent(Intent intent) {
        repoId = getIntent().getExtras().getInt(Constants.REPO_ID);
    }

    @Override
    public void displayRepoDetail(Repo repo) {
        hideLoading();
        if(repo != null){
            this.repo = repo;
            getSupportActionBar().setTitle(repo.getName());
            repo_title.setText(repo.getName());
            repo_description.setText(repo.getFullName());
            repo_full_description.setText(repo.getDescription());
            repo_watchers.setText(String.format("%,d",repo.getWatchers()));
            repo_stars.setText(String.format("%,d",repo.getStargazersCount()));
            repo_forks.setText(String.format("%,d",repo.getForksCount()));
            repo_open_issues.setText(String.format("%,d",repo.getOpenIssuesCount()));
            repo_language.setText(repo.getLanguage());

            Picasso.with(this)
                    .load(repo.getOwner().getAvatarUrl())
                    .into(ownerAvatar);
        }
    }


    @OnClick(R.id.btn_view_on_github)
    public void viewOnGithubButtonClicked(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(repo.getHtmlUrl()));
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
