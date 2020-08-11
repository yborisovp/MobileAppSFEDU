package com.example.news_vk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfedymob.R;

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



public class NewsActivity extends AppCompatActivity {

    //private CustomArrayAdapter adapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    TextAndImageAdapter textAndImageAdapter;
    GridView grid;
    //private List<ListItemClass> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_grid_fragment);
        grid = findViewById(R.id.news_grid_items);
        
        gridInit();

    }
    /*
     *
     *
     *
     *
     *
     * */
    public void vkApiInit() {

    }

    /*
    *
    *
    *
    *
    *
    * */
    private void gridInit() {
        grid = findViewById(R.id.news_grid_items);
        get_posts();
    }
    /*private void init()
    {
        ListView listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        adapter = new CustomArrayAdapter(this, R.layout.news_list_item, arrayList, getLayoutInflater());
        listView.setAdapter(adapter);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        Thread secThread = new Thread(runnable);
        secThread.start();
    }

    private void getWeb()
    {

        try {
            Document doc = Jsoup.connect("https://vk.com/sed_announce").get();
            Elements tables = doc.select("div[class^=wall_post_text]");;

            for (int i=0;i<tables.size();i++)
            {
                ListItemClass items = new ListItemClass();
                items.setData_1(tables.get(i).text());
                arrayList.add(items);
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    private void get_posts() {
        Disposable disposable = Single.fromCallable(this::requestToSite)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(__ -> Toast.makeText(this, "parse Error", Toast.LENGTH_LONG).show())
                .subscribe(document ->
                    grid.setAdapter(new TextAndImageAdapter(this, getListOfItems(document)))
                    ,
                    throwable -> Log.e("SUBSCRIBER", "get_post method crushed", throwable));
        compositeDisposable.add(disposable);

    }

    private List<ListItem> getListOfItems(Document document) {
        List<ListItem> postsList =  new ArrayList<>();
        Elements elements = document.select(".post_content");
        for (Element el : elements) {
            postsList.add(getListItem(el));
        }
        return postsList;
    }

    private ListItem getListItem(Element element) {
        String text_tv = null;

        text_tv = element.select(".wall_post_text").text();

        String uri_iv = null;
        uri_iv = element.select("div.page_post_sized_thumbs").select("a").attr("style");
        if (!uri_iv.equals(""))
            uri_iv = uri_iv.substring(uri_iv.indexOf("https://"), uri_iv.indexOf(")"));

        return new ListItem(text_tv, uri_iv);
    }

    private Document requestToSite() {
        try {
            return Jsoup.connect("https://vk.com/sed_announce").get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
