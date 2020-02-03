package apachecms.ar.com.unimedicalapp.Data.remote;

/**
 * Created by mauricioampuero on 26/08/2019.
 */

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://186.12.169.244/unimedicalBe/web/api/";

    public static ApiService getApiService() {

        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
