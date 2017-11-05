package com.example.musta.simplyshare;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class PopulateData {

    public static ArrayList<FileModel> getMusicInfos(Context context) {

        ArrayList<FileModel> musicInfos = new ArrayList<FileModel>();

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor == null) {
            return null;
        }


        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();


            int isMusic = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));

            if (isMusic != 0) {
                FileModel music = new FileModel();


                music.path = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));

                if (!new File(music.path).exists()) {
                    continue;
                }


                music.id = String.valueOf(cursor.getLong(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media._ID)));

//                music.songTitle = cursor.getString(cursor
//                        .getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));


                music.name = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));

                music.size = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

                music.date = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.DATE_ADDED));


//                music.album = cursor.getString(cursor
//                        .getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
//
//
//                music.songArtist = cursor.getString(cursor
//                        .getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));

//                music.duration = cursor
//                        .getLong(cursor
//                                .getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));

                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                mmr.setDataSource(music.path);
                mmr.release();

                musicInfos.add(music);
            }
        }

        return musicInfos;
    }
}
