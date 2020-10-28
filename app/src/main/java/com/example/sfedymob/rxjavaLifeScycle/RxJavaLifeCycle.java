package com.example.sfedymob.rxjavaLifeScycle;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RxJavaLifeCycle {
    private CompositeDisposable compositeDisposable;

    public RxJavaLifeCycle (){
        this.compositeDisposable = new CompositeDisposable();
    }

    public void dispose() {
        compositeDisposable.clear();
    }

    public void add(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
