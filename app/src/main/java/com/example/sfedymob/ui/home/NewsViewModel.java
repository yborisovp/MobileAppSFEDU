package com.example.sfedymob.ui.home;

import android.widget.GridView;
import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news_vk.TextAndImageAdapter;

public class NewsViewModel extends ViewModel {


    private MutableLiveData<ListView> listView;

    public NewsViewModel() {
        listView = new MutableLiveData<>();
        //        mText = new MutableLiveData<>();
//        mText.setValue("");
        listView.setValue(listView.getValue());
    }


    public LiveData<ListView> getText() {
        return listView;
    }
}