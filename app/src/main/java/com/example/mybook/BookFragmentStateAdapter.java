package com.example.mybook;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BookFragmentStateAdapter extends FragmentStateAdapter {
private FragmentActivity _fragmentActivity;
    private List<String> books;


    public BookFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.books = this.intDatas();
        _fragmentActivity=fragmentActivity;
    }

    private List<String> intDatas()  {
        List<String> list = new ArrayList<String>();
        list.add("dog1.jpg");
        list.add("dog2.jpg");
        list.add("dog3.jpg");
        list.add("dog4.png");
        list.add("dog5.png");

        return list;


    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        play();
        return new BookPageFragment(this.books.get(position));
    }

    public void play(){
        MediaPlayer mPlayer= MediaPlayer.create(getApplicationContext(), R.raw.listsound);
        mPlayer.start();
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

    public Context getApplicationContext() {

        return _fragmentActivity.getApplicationContext();
    }
}