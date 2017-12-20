package com.example.musta.simplyshare.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musta.simplyshare.ApplicationTab.ApplicationModel;
import com.example.musta.simplyshare.FilesTab.FileModel;
import com.example.musta.simplyshare.Model.Music;
import com.example.musta.simplyshare.MusicTab.MusicModel;
import com.example.musta.simplyshare.PicturesTab.PictureModel;
import com.example.musta.simplyshare.R;
import com.example.musta.simplyshare.VideosTab.VideoModel;
import com.skyfishjy.library.RippleBackground;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SEND_LIST_APPS = "argappsendlist";
    private static final String ARG_SEND_LIST_FILES = "argfilessendlist";
    private static final String ARG_SEND_LIST_MUSIC = "argmusicsendlist";
    private static final String ARG_SEND_LIST_PICTURES = "argpicturessendlist";
    private static final String ARG_SEND_LIST_VIDEOS = "argvideossendlist";

    // TODO: Rename and change types of parameters
    private ArrayList<ApplicationModel> sendListApps;
    private ArrayList<FileModel> sendListFiles;
    private ArrayList<MusicModel> sendListMusic;
    private ArrayList<PictureModel> sendListPictures;
    private ArrayList<VideoModel> sendListVideos;

    RadarView mRadarView = null;

    public static SendFragment newInstance(ArrayList<ApplicationModel> sendListApps, ArrayList<FileModel> sendListFiles,
                        ArrayList<MusicModel> sendListMusic, ArrayList<PictureModel> sendListPictures,
                        ArrayList<VideoModel> sendListVideos) {
        SendFragment fragment = new SendFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_SEND_LIST_APPS, sendListApps != null && !sendListApps.isEmpty() ? sendListApps : new ArrayList<ApplicationModel>());
        args.putParcelableArrayList(ARG_SEND_LIST_FILES, sendListFiles != null && !sendListFiles.isEmpty() ? sendListFiles : new ArrayList<FileModel>());
        args.putParcelableArrayList(ARG_SEND_LIST_MUSIC, sendListMusic != null && !sendListMusic.isEmpty() ? sendListMusic : new ArrayList<MusicModel>());
        args.putParcelableArrayList(ARG_SEND_LIST_PICTURES, sendListPictures != null && !sendListPictures.isEmpty() ? sendListPictures : new ArrayList<PictureModel>());
        args.putParcelableArrayList(ARG_SEND_LIST_VIDEOS, sendListVideos != null && !sendListVideos.isEmpty() ? sendListVideos : new ArrayList<VideoModel>());
        fragment.setArguments(args);
        return fragment;
    }

    public SendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sendListApps = getArguments().getParcelableArrayList(ARG_SEND_LIST_APPS);
            sendListFiles = getArguments().getParcelableArrayList(ARG_SEND_LIST_FILES);
            sendListMusic = getArguments().getParcelableArrayList(ARG_SEND_LIST_MUSIC);
            sendListPictures = getArguments().getParcelableArrayList(ARG_SEND_LIST_PICTURES);
            sendListVideos = getArguments().getParcelableArrayList(ARG_SEND_LIST_VIDEOS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send, container, false);
        /*final RadarView radarView = (RadarView) view.findViewById(R.id.radar_view);
        radarView.setReferencePoint(new RadarPoint("center", 44.139644f,12.246429f));
        radarView.startAnimation();*/
//        final RippleBackground rippleBackground= (RippleBackground) view.findViewById(R.id.content);
//       rippleBackground.startRippleAnimation();
        mRadarView = (RadarView) view.findViewById(R.id.radarView);
        mRadarView.setShowCircles(true);
        if (mRadarView != null) mRadarView.startAnimation();
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RadarPoint r1 = new RadarPoint("identifier1", 44.139175f,12.247117f, "http://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg");
                RadarPoint r2 = new RadarPoint("identifier2", 44.138205f,12.248533f, "http://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg");
                RadarPoint r3 = new RadarPoint("identifier3", 44.137265f,12.250056f, "http://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg");
                RadarPoint r4 = new RadarPoint("identifier4", 44.134374f,12.251215f, "http://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg");
                RadarPoint r5 = new RadarPoint("identifier5", 44.132491f,12.248833f, "http://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg");
                RadarPoint r6 = new RadarPoint("identifier6", 44.130676f,12.248908f, "http://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg");
                RadarPoint r7 = new RadarPoint("identifier7", 44.128889f,12.248286f, "http://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg");
                RadarPoint r8 = new RadarPoint("identifier8", 44.124769f,12.242053f, "http://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg");
                RadarPoint r9 = new RadarPoint("identifier9", 44.118592f,12.242053f, "http://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg");
                RadarPoint r10 = new RadarPoint("identifier10", 44.116289f,12.240840f, "http://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg");
                ArrayList<RadarPoint> points = new ArrayList<>();
                points.add(r1);
                points.add(r2);
                points.add(r3);
                points.add(r4);
                points.add(r5);
                points.add(r6);
                points.add(r7);
                points.add(r8);
                points.add(r9);
                points.add(r10);

                radarView.setPoints(points);

            }
        }, 10000);*/
        return view;
    }

}
