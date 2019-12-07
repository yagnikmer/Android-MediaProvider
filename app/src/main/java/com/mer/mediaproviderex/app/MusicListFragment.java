package com.mer.mediaproviderex.app;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mer.mediaproviderex.R;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class MusicListFragment extends Fragment {

    ListView mListView;
    Context mContext;
    int mTabIndex;
    ArrayList<String> mSongsList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_music, container, false);
        mListView = root.findViewById(R.id.lvlist);
        mTabIndex = getArguments().getInt("index");
        mListView.setAdapter(new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, getMediaInfo()));
        return root;
    }

    private ArrayList<String> getMediaInfo() {
        Uri uri;
        if (mTabIndex == 0)
            uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
        else
            uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {"title", "duration"};
        Cursor cursor = mContext.getContentResolver().query(uri, projection, null, null, null);
        cursor.moveToFirst();
        do {
            Music song = new Music();
            song.title = cursor.getString(0);
            song.duration = cursor.getString(1);
            mSongsList.add(song.title);
        } while (cursor.moveToNext());
        return mSongsList;
    }
}
