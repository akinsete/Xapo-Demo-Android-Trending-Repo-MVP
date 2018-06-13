package trendingrepo.test.demo.xapo.app.data.github;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import trendingrepo.test.demo.xapo.app.data.models.GithubSearchResponse;
import trendingrepo.test.demo.xapo.app.data.models.Repo;

/**
 * Created by sundayakinsete on 13/06/2018.
 */

public interface GithubApi {
    @GET("search/repositories?q=android%20language:java&sort=stars&order=desc&per_page=10")
    rx.Observable<GithubSearchResponse> getRepositories(@Query("page") int page);

    @GET("repositories/{repo_id}")
    rx.Observable<Repo> getRepo(@Path("repo_id") int repo_id);
}
