package apachecms.ar.com.unimedicalapp.ui.lenders;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import apachecms.ar.com.unimedicalapp.Adapters.LenderListAdapter;
import apachecms.ar.com.unimedicalapp.Data.model.ResponseLender;
import apachecms.ar.com.unimedicalapp.Data.remote.ApiService;
import apachecms.ar.com.unimedicalapp.Data.remote.ApiUtils;
import apachecms.ar.com.unimedicalapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LendersFragment extends Fragment {

    private LendersViewModel lendersViewModel;
    private ApiService mApiService;
    public ListView mListView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lendersViewModel =
                ViewModelProviders.of(this).get(LendersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        mListView = root.findViewById(R.id.listView);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        lendersViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        get();
        return root;
    }

    public void get(){
        final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
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
                    Toast.makeText(getActivity(), "Code: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ResponseLender responseLender = response.body();
                LenderListAdapter adapter = new LenderListAdapter(getActivity(),R.layout.adapter_view_layout,responseLender.getResults());
                mListView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<ResponseLender> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Toast.makeText(getActivity(), "Fallo"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}