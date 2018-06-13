package trendingrepo.test.demo.xapo.app.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

public class BaseActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setupBaseActionbar(Toolbar toolBar, String title){
        if (toolBar != null) {
            setSupportActionBar(toolBar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if(!TextUtils.isEmpty(title)){
                getSupportActionBar().setTitle(title);
            }
        }
    }


    public void showLoading(String message){
        progressDialog = ProgressDialog.show(this, null, message);
    }

    public void hideLoading(){
        if(progressDialog != null){
            if(progressDialog.isShowing()){
                progressDialog.cancel();
            }
        }
    }
}
