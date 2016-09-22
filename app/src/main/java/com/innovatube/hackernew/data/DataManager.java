package com.innovatube.hackernew.data;

import com.innovatube.hackernew.data.local.PreferenceHelper;
import com.innovatube.hackernew.data.local.RealmHelper;
import com.innovatube.hackernew.data.model.Story;
import com.innovatube.hackernew.data.model.UserId;
import com.innovatube.hackernew.data.prefs.UserPrefs;
import com.innovatube.hackernew.data.remote.HackerNewsService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by TOIDV on 4/5/2016.
 */

@Singleton
public class DataManager {
    private final HackerNewsService hackerNewsService;
    private final PreferenceHelper preferenceHelper;
    private final RealmHelper realmHelper;

    @Inject
    public DataManager(HackerNewsService inploiService, PreferenceHelper preferenceHelper, RealmHelper realmHelper) {
        this.hackerNewsService = inploiService;
        this.preferenceHelper = preferenceHelper;
        this.realmHelper = realmHelper;
    }

    public boolean isLogin() {
        UserPrefs userPrefs = preferenceHelper.getUserPrefs();
        return userPrefs.getUserId() > 0;
    }


    private String getToken() {
        UserPrefs userPrefs = preferenceHelper.getUserPrefs();
        return userPrefs.getAccessToken();
    }

    public int getUserId() {
        UserPrefs userPrefs = preferenceHelper.getUserPrefs();
        return userPrefs.getUserId();

    }


    public void saveUserId(int userId) {
        UserPrefs userPrefs = preferenceHelper.getUserPrefs();
        userPrefs.setUserId(userId);
    }

    public void logout() {
        clearPreference();
    }

    private void clearPreference() {
        UserPrefs userPrefs = preferenceHelper.getUserPrefs();
        userPrefs.setUserId(-1);
        userPrefs.setFirstName("");
        userPrefs.setLastName("");
        userPrefs.setAccessToken("");
        userPrefs.setProfilePicture("");
        userPrefs.setEmail("");
    }

    public Observable<UserId> createAccount(String firstName,
                                            String lastName,
                                            String emailAddress,
                                            String password,
                                            String confirmPassword,
                                            String dob,
                                            String promotionCode) {
//        return hackerNewsService.createJobSeekerAccount(firstName, lastName, emailAddress, password, confirmPassword, dob, promotionCode);
        return null;
    }

    public void saveUserInfo(UserId userId, Realm realm) {
        realmHelper.saveUserInfo(userId, realm);
    }

    public Observable<List<Long>> getTopStoriesFromRemote() {
        return hackerNewsService.getTopStories();
    }

    public Observable<List<Long>> getNewStoriesFromRemote() {
        return hackerNewsService.getNewStories();
    }

    public void saveStories(Realm realm, List<Story> stories) {
        realmHelper.saveStories(realm, stories);
    }

    public Observable<RealmResults<Story>> fetchTopStories(Realm realm) {
        return realmHelper.getTopStories(realm);

    }

    public Observable<RealmResults<Story>> fetchNewStories(Realm realm) {
        return realmHelper.getNewStories(realm);

    }

    public Observable<Story> getStoryDetailFromRemote(long storyId) {
        return hackerNewsService.getStoryDetail(storyId);
    }

    public void saveStory(Realm realm, Story story) {
        realmHelper.saveStory(realm, story);
    }

    public Observable<RealmObject> fetchStory(Realm realm, long storyId) {
        return realmHelper.getStory(realm, storyId);
    }

}