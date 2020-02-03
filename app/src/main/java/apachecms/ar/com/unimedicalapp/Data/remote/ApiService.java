package apachecms.ar.com.unimedicalapp.Data.remote;

import apachecms.ar.com.unimedicalapp.Data.model.ResponseLender;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("lenders/search?limit=100")
    Call<ResponseLender> getLenders();
}
