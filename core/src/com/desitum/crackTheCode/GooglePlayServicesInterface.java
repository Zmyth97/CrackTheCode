package com.desitum.crackTheCode;

/**
 * Created by zmos1505 on 2/10/2015.
 */
public interface GooglePlayServicesInterface {

    public void getRegularLeaderboard();
    public void getEndlessLeaderboard();
    public void submitRegularScore(int score);
    public void submitEndlessScore(int score);
    public void showAd();
    public void hideAd();
    public void unlockAchievement(int achievement);
    public void login();
    public void logout();
    public void shareRegularScore(int score);
    public void shareEndlessScore(int score);
}
