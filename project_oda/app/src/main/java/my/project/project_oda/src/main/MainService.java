package my.project.project_oda.src.main;

import android.util.Log;

import org.json.JSONObject;

import my.project.project_oda.src.main.interfaces.MainActivityView;
import my.project.project_oda.src.main.interfaces.MainRetrofitInterface;
import my.project.project_oda.src.main.home.models.DirectOrderResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static my.project.project_oda.src.ApplicationClass.*;

class MainService {

    MainActivityView mMainActivityView;
    JSONObject mParams;

    MainService(final MainActivityView mainActivityView, JSONObject mParams) {
        this.mMainActivityView = mainActivityView;
        this.mParams = mParams;
    }


}
