package com.innovatube.hackernew.data.local;

import com.innovatube.hackernew.consts.Consts;
import com.innovatube.hackernew.data.model.Story;
import com.innovatube.hackernew.data.model.UserId;

import java.util.List;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by TOIDV on 8/18/2016.
 */
@Singleton
public class RealmHelper {

    public Observable<RealmObject> getUserId(Realm realm) {
        return realm.where(UserId.class).findFirstAsync()
                .asObservable()
                .filter(new Func1<RealmObject, Boolean>() {
                    @Override
                    public Boolean call(RealmObject realmObject) {
                        return realmObject.isLoaded();
                    }
                })
                .filter(new Func1<RealmObject, Boolean>() {
                    @Override
                    public Boolean call(RealmObject realmObject) {
                        return realmObject.isValid();
                    }
                });
    }

    public void saveUserInfo(final UserId userId, Realm realm) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(userId);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    public Observable<RealmObject> getStory(final Realm realm, long storyId) {
        return realm.where(Story.class).equalTo("id", storyId).findFirst()
                .asObservable()
                .filter(new Func1<RealmObject, Boolean>() {
                    @Override
                    public Boolean call(RealmObject realmObject) {
                        return realmObject.isLoaded();
                    }
                })
                .filter(new Func1<RealmObject, Boolean>() {
                    @Override
                    public Boolean call(RealmObject realmObject) {
                        return realmObject.isValid();
                    }
                });
    }

    public void saveStory(Realm realm, final Story story) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(story);
            }
        });
    }

    public Observable<RealmResults<Story>> getTopStories(Realm realm) {
        return realm.where(Story.class).equalTo("category", Consts.STORY_CATEGORY_TOP).findAll()
                .asObservable();
    }

    public Observable<RealmResults<Story>> getNewStories(Realm realm) {
        return realm.where(Story.class).equalTo("category", Consts.STORY_CATEGORY_NEW).findAll()
                .asObservable();
    }

    public void saveStories(Realm realm, final List<Story> stories) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(stories);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

}