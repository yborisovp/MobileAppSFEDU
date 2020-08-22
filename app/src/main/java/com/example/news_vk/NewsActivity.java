package com.example.news_vk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfedymob.R;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiGroups;
import com.vk.sdk.api.methods.VKApiWall;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class NewsActivity extends Activity {

    //private CustomArrayAdapter adapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    TextAndImageAdapter textAndImageAdapter;
    GridView grid;
    //private List<ListItemClass> arrayList;

    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_grid_fragment);


        VKSdk.login(this);
        listView = (ListView)findViewById(R.id.listView);

    }

    @Override
    protected void onActivityResult(int reqeustCode, int resultCode, Intent data)
    {
        if (!VKSdk.onActivityResult(reqeustCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                VKRequest vkRequest = new VKApiGroups().getById(VKParameters.from("group_ids", "sed_announce"));
                vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);

                        VKList vkList = (VKList) response.parsedModel;
                        try {

                            for (int j =0; j<=300; j+=100) {
                                VKRequest vkRequest1 = new VKApiWall().get(VKParameters.from(VKApiConst.OWNER_ID, "-" + vkList
                                        .get(0).fields.getInt("id"), VKApiConst.COUNT, 100, VKApiConst.OFFSET, j));

                                vkRequest1.executeWithListener(new VKRequest.VKRequestListener() {
                                    @Override
                                    public void onComplete(VKResponse response) {
                                        super.onComplete(response);

                                        try {
                                            JSONObject jsonObject = (JSONObject) response.json.get("response");
                                            JSONArray jsonArray = (JSONArray) jsonObject.get("items");
                                            for (int i = 0; i != jsonArray.length(); i++) {
                                                JSONObject post = (JSONObject) jsonArray.get(i);

                                                if (!post.getString("text").equals("")) {
                                                    arrayList.add(post.getString("text"));
                                                }
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        arrayAdapter = new ArrayAdapter<String>(NewsActivity.this, android.R.layout.simple_list_item_1, arrayList);
                                        listView.setAdapter(arrayAdapter);
                                    }
                                });
                            }

                        } catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                });

            }

            @Override
            public void onError(VKError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        })) {
            super.onActivityResult(reqeustCode, resultCode, data);
        }
    }
}
