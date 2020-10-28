package com.example.sfedymob.ui.news;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.news_vk.ListItem;
import com.example.news_vk.TextAndImageAdapter;
import com.example.sfedymob.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsFragment extends Fragment {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private TextAndImageAdapter textAndImageAdapter;
    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_news, container, false);

        listView = root.findViewById(R.id.newsListView);
        get_posts();

        // newsViewModel.getText().observe(getViewLifecycleOwner(), gridView -> get_posts(gView, getActivity()));
        return root;
    }

    private void get_posts() {
        Disposable disposable = Single.fromCallable(() -> requestToSite())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(__ -> Toast.makeText(getContext(), "parse Error", Toast.LENGTH_LONG).show())
                .subscribe(document ->
                                listView.setAdapter(new TextAndImageAdapter(getContext(), getListOfItems(document)))
                        ,
                        throwable -> Log.e("SUBSCRIBER", "get_post method crushed", throwable));
        compositeDisposable.add(disposable);

    }

    private List<ListItem> getListOfItems(Document document) {
        List<ListItem> postsList = new ArrayList<>();
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