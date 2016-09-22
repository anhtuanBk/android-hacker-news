package com.innovatube.hackernew.ui.newshome;

import com.innovatube.hackernew.consts.Consts;
import com.innovatube.hackernew.data.DataManager;
import com.innovatube.hackernew.ui.base.BasePresenter;
import com.innovatube.hackernew.utils.InnovatubeUtils;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cat on 9/22/2016.
 */

public class NewsPresenter extends BasePresenter<NewsMvpView> {

    private final DataManager dataManager;
    private final Retrofit retrofit;
    private final Realm realm;

    private Subscription subscription;

    @Inject
    NewsPresenter(DataManager dataManager, Retrofit retrofit, Realm realm) {
        this.dataManager = dataManager;
        this.realm = realm;
        this.retrofit = retrofit;
    }

    @Override
    public void detachView() {
        if (subscription != null) subscription.unsubscribe();
        super.detachView();
    }

    public void getStories(int category) {
        switch (category) {
            case Consts.STORY_CATEGORY_TOP:
                dataManager.getTopStoriesFromRemote()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<Long>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                getMvpView().showAlertDialog(InnovatubeUtils.getError(e, retrofit));
                            }

                            @Override
                            public void onNext(List<Long> longs) {

                            }
                        });

        }
    }

}
