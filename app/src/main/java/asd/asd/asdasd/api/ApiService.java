package asd.asd.asdasd.api;


import android.database.Observable;

import asd.asd.asdasd.api.bean.HJBaseEntity;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/7/2.
 */

public interface ApiService {
    
    
    
    @GET()
    Call<ResponseBody> getdata(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Observable<HJBaseEntity<String>> getStatus(@Url String url, @QueryMap Map<String, String> map);

}
