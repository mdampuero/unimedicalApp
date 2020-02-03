package apachecms.ar.com.unimedicalapp;

import androidx.appcompat.app.AppCompatActivity;
import apachecms.ar.com.unimedicalapp.Adapters.LenderListAdapter;
import apachecms.ar.com.unimedicalapp.Data.model.Lender;
import apachecms.ar.com.unimedicalapp.Data.model.ResponseLender;
import apachecms.ar.com.unimedicalapp.Data.remote.ApiService;
import apachecms.ar.com.unimedicalapp.Data.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ApiService mApiService;
    public  ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);

        get();
    }

    public void get(){
        final ProgressDialog mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getResources().getString(R.string.app_name)+" - Cargando...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();


        mApiService = ApiUtils.getApiService();
        mApiService.getLenders().enqueue(new Callback<ResponseLender>() {
            @Override
            public void onResponse(Call<ResponseLender> call, Response<ResponseLender> response) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Code: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ResponseLender responseLender = response.body();
                LenderListAdapter adapter = new LenderListAdapter(MainActivity.this,R.layout.adapter_view_layout,responseLender.getResults());
                mListView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<ResponseLender> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Fallo"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
