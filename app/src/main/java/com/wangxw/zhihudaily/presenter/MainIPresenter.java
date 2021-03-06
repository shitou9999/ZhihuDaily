package com.wangxw.zhihudaily.presenter;

import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.wangxw.zhihudaily.api.ApiManager;
import com.wangxw.zhihudaily.base.BaseIView;
import com.wangxw.zhihudaily.base.BasePresenter;
import com.wangxw.zhihudaily.bean.LatestNews;
import com.wangxw.zhihudaily.contract.MainContract;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangxw on 2017/1/11.
 * E-mail : wangxw725@163.com
 * function :
 */

public class MainIPresenter extends BasePresenter<MainContract.IView> implements MainContract.IPresenter {

    private String mCurrentDate;

    public MainIPresenter(MainContract.IView mIView) {
        super(mIView);
    }

    @Override
    public void bindView(BaseIView view) {
        mView = (MainContract.IView) view;
    }

    @Override
    public void getNewsFromServer() {
        ApiManager
                .getZhihuApi()
                .getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LatestNews>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.i("onError:"+e.toString());
                    }

                    @Override
                    public void onNext(LatestNews latestNews) {
                        mView.initViewData(latestNews);
                        mCurrentDate =latestNews.getDate();
                    }
                });
    }

    @Override
    public void loadBeforStories() {
        ApiManager.getZhihuApi()
                .getBeforeNews(mCurrentDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LatestNews>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.i("onError:"+e.toString());
                    }

                    @Override
                    public void onNext(LatestNews latestNews) {
                        mCurrentDate = latestNews.getDate();
                        mView.loadBeforeStories(latestNews);
                    }
                });
    }
}
