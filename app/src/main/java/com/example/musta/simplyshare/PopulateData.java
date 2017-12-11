package com.example.musta.simplyshare;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.MutableInt;
import android.webkit.MimeTypeMap;

import com.example.musta.simplyshare.MusicTab.MusicModel;
import com.example.musta.simplyshare.PicturesTab.PictureModel;
import com.example.musta.simplyshare.VideosTab.VideoModel;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class PopulateData {

    public static ArrayList<MusicModel> getMusicInfo(Context context) {
        ArrayList<MusicModel> musicInfos = new ArrayList<MusicModel>();

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor == null) {
            return null;
        }
        MediaMetadataRetriever mmr;


        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();


            int isMusic = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));

            if (isMusic != 0) {
                MusicModel music = new MusicModel();


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

                System.out.println("Msusic mime: " + cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.MIME_TYPE)));
//                music.album = cursor.getString(cursor
//                        .getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
//
//
//                music.songArtist = cursor.getString(cursor
//                        .getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));

//                music.duration = cursor
//                        .getLong(cursor
//                                .getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));

//                mmr = new MediaMetadataRetriever();
//                mmr.setDataSource(music.path);
//                mmr.release();

                System.out.println("Music ext: " + music.path);
                String ext = music.path;
                ext = ext.substring(ext.lastIndexOf(".") + 1).trim();
                System.out.println(ext);
                music.ext = ext;
                musicInfos.add(music);
            }
        }

        return musicInfos;
    }

    public static ArrayList<FileModel> getFileInfo(Context context) {

        ContentResolver cr = context.getContentResolver();
        Uri uri = MediaStore.Files.getContentUri("external");
        String[] projection = null;
//        String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
//                + MediaStore.Files.FileColumns.MEDIA_TYPE_NONE;
        String sortOrder = null; // unordered
        String selection = MediaStore.Files.FileColumns.MIME_TYPE + "=?";
        String mimeTypePDF = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf");
//        String mimeTypeDoc = MimeTypeMap.getSingleton().getMimeTypeFromExtension("doc");
        String[] selectionArgs = new String[]{mimeTypePDF};
        Cursor allNonMediaFiles = cr.query(uri, projection, selection, selectionArgs, sortOrder);
        ArrayList<FileModel> fileList = new ArrayList<FileModel>();

        for (int i = 0; i < allNonMediaFiles.getCount(); i++) {
            allNonMediaFiles.moveToNext();
            FileModel fileModel = new FileModel(allNonMediaFiles.getString(0), allNonMediaFiles.getString(8), allNonMediaFiles.getString(2));
            System.out.println("File details: " + allNonMediaFiles.getString(1));
            String ext = allNonMediaFiles.getString(1);
            System.out.println(ext.substring(ext.lastIndexOf(".") + 1).trim());
            System.out.println(ext);
            ext = ext.substring(ext.lastIndexOf(".") + 1).trim();
            fileModel.ext = ext;
            fileList.add(fileModel);

        }

        return fileList;
    }

    public static ArrayList<PictureModel> getPicutreInfo(Context context) {

        ArrayList<PictureModel> pictureInfo = new ArrayList<PictureModel>();

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Images.Media.DEFAULT_SORT_ORDER);
        if (cursor == null) {
            return null;
        }


        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();


//            int isMusic = cursor.getInt(cursor
//                    .getColumnIndex(MediaStore.Images.Media.CONTENT_TYPE));

//            if (isMusic != 0) {
            PictureModel picture = new PictureModel();


            picture.path = cursor.getString(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));

            if (!new File(picture.path).exists()) {
                continue;
            }


            picture.id = String.valueOf(cursor.getLong(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media._ID)));

//                music.songTitle = cursor.getString(cursor
//                        .getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));


            picture.name = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));

            picture.size = cursor.getString(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

            picture.date = cursor.getString(cursor
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

//            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
//            mmr.setDataSource(picture.path);
//            mmr.release();

            System.out.println("Picture ext: " + picture.path);
            String ext = picture.path;
            System.out.println(ext.substring(ext.lastIndexOf(".") + 1).trim());
            ext = ext.substring(ext.lastIndexOf(".") + 1).trim();
            picture.ext = ext;
            pictureInfo.add(picture);
//            }
        }

        return pictureInfo;
    }

    public static ArrayList<VideoModel> getVideoInfo(Context context) {

        ArrayList<VideoModel> videoInfo = new ArrayList<VideoModel>();

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Video.Media.DEFAULT_SORT_ORDER);
        if (cursor == null) {
            return null;
        }


        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();


//            int isMusic = cursor.getInt(cursor
//                    .getColumnIndex(MediaStore.Images.Media.));

//            if (isMusic != 0) {
            VideoModel video = new VideoModel();


            video.path = cursor.getString(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));

            if (!new File(video.path).exists()) {
                continue;
            }


            video.id = String.valueOf(cursor.getLong(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media._ID)));

//                music.songTitle = cursor.getString(cursor
//                        .getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));


            video.name = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));

            video.size = cursor.getString(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

            video.date = cursor.getString(cursor
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

//            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
//            mmr.setDataSource(video.path);
//            mmr.release();

            System.out.println("Video ext: " + video.path);
            String ext = video.path;
            System.out.println(ext.substring(ext.lastIndexOf(".") + 1).trim());
            ext = ext.substring(ext.lastIndexOf(".") + 1).trim();
            video.ext = ext;
            videoInfo.add(video);
//            }
        }

        return videoInfo;
    }
}
