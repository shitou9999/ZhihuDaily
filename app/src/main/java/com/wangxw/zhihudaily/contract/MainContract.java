package com.wangxw.zhihudaily.contract;

import com.wangxw.zhihudaily.base.BaseIPresenter;
import com.wangxw.zhihudaily.base.BaseIView;
import com.wangxw.zhihudaily.bean.LatestNews;

/**
 * Created by wangxw on 2017/1/12 0012.
 * E-mail:wangxw725@163.com
 * function:
 */
public interface MainContract {

    interface IView extends BaseIView {
        void initViewData(LatestNews latestNews);

        void loadBeforeStories(LatestNews latestNews);
    }

    interface IPresenter extends BaseIPresenter {

        void getNewsFromServer();

        void loadBeforStories();
    }

}
