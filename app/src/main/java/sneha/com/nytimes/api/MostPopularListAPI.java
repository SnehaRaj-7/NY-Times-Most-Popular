package sneha.com.nytimes.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import sneha.com.nytimes.models.MostPopularResponse;

/**
 * Created by HP on 07-08-2018.
 */
public interface MostPopularListAPI {

    @GET("mostviewed/all-sections/1.json?api-key=8bb1539341604df5a7d7fb1e2ab82dca")
    Call<MostPopularResponse> getData(
            @Header("time-period") String time,
            @Header("section") String section
            );
}
