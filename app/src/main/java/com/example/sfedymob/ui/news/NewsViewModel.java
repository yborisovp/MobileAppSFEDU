package com.example.sfedymob.ui.news;

import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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