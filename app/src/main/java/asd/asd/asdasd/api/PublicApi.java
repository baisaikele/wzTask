package asd.asd.asdasd.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import asd.asd.asdasd.IpBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicApi {


    public static void getRefreshApi(final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
//        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.USER_REFRESH);
//        Api.getDefault().getdata(ApiConstant.USER_REFRESH, stringStringHashMap).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    try {
//                        String result = response.body().string();
//                        JSONObject jsonObject = new JSONObject(result);
//                        int code = jsonObject.getInt("code");
//                        listener.success(new Integer(new Integer(code)));
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        listener.success(401);
//
//                    }
//
//                } else {
//                    try {
//                        String result = response.errorBody().string();
//                        JSONObject jsonObject = new JSONObject(result);
//                        int code = jsonObject.getInt("code");
//                        listener.success(new Integer(code));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        listener.success(new Integer(401));
//
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });
    }

    public static void getRefreshApiIP(final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
//        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.USER_REFRESH);
        Api.getDefault().getdata(ApiConstant.IP, map1).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string();
                        IpBean ipBean = new Gson().fromJson(result, IpBean.class);
                        listener.success(ipBean);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        getRefreshApiIP(listener);

                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public interface ResponseListener {
        void success(Object o);
        void error(String s);
    }

}
