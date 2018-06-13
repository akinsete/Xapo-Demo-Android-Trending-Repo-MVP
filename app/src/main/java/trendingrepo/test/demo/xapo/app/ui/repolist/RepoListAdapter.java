package trendingrepo.test.demo.xapo.app.ui.repolist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import trendingrepo.test.demo.xapo.app.R;
import trendingrepo.test.demo.xapo.app.data.models.Repo;

public class RepoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    RepoListAdapterListener listener;

    public void setRepos(List<Repo> repos) {
        this.repos  = repos;
        notifyDataSetChanged();
    }

    interface RepoListAdapterListener {

        void onItemClicked(Repo repo);

    }

    private List<Repo> repos;

    public RepoListAdapter(List<Repo> repos) {
        this.repos = repos;
    }

    public void setRepoListAdapterListener(RepoListAdapterListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_item,parent,false);
        return new RepoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RepoListViewHolder repoListViewHolder = (RepoListViewHolder)  holder;
        repoListViewHolder.setRepo(repos.get(position));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }



    public class RepoListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.repo_title) TextView repoTitle;
        @BindView(R.id.repo_description) TextView repoDescription;
        @BindView(R.id.repo_watchers) TextView repoWatchers;
        @BindView(R.id.repo_language) TextView repoLanguage;
        @BindView(R.id.owner_avatar) ImageView ownerAvatar;

        public RepoListViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }

        void setRepo(final Repo repo){

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onItemClicked(repo);
                    }
                }
            });

            repoTitle.setText(repo.getName());
            repoDescription.setText(repo.getFullName());
            repoLanguage.setText(repo.getLanguage());
            repoWatchers.setText(String.format("%,d",repo.getForksCount()));

            Picasso.with(itemView.getContext())
                    .load(repo.getOwner().getAvatarUrl())
                    .into(ownerAvatar);
        }

    }



}
