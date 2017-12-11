package com.example.musta.simplyshare;

import android.Manifest;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.musta.simplyshare.ApplicationTab.ApplicationModel;
import com.example.musta.simplyshare.MusicTab.MusicAdapter;
import com.example.musta.simplyshare.MusicTab.MusicModel;
import com.example.musta.simplyshare.FilesTab.FileModel;
import com.example.musta.simplyshare.PicturesTab.PictureModel;
import com.example.musta.simplyshare.Services.FileServerAsyncTask;
import com.example.musta.simplyshare.Services.WiFiDirectBroadcastReceiver;
import com.example.musta.simplyshare.VideosTab.VideoModel;

/**
 * Created by MA_Laptop on 11/28/2017.
 */

public class SendFiles extends AppCompatActivity {

    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private WiFiDirectBroadcastReceiver mReceiver;
    private IntentFilter mIntentFilter;
    ApplicationModel applicationModel;
    FileModel fileModel;
    MusicModel musicModel;
    PictureModel pictureModel;
    VideoModel videoModel;

    Button btnSend, btnConnect;
    TextView txtConnected;
    Context context;
    private FileServerAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        btnSend = (Button) findViewById(R.id.btnSend);
        txtConnected = (TextView) findViewById(R.id.txtConnected);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        context = getApplicationContext();
//        ApplicationModel applicationModel = (ApplicationModel) savedInstanceState.getSerializable("sendingObject");
//        System.out.println(applicationModel.packageName);
        final Object object = this.getIntent().getExtras().getSerializable("sendingObject");
//        System.out.println("HEREEE"+((MusicModel) object).name);
//        System.out.println("HEREEE"+((MusicModel) object).ext);
//        System.out.println("HEREEE"+((MusicModel) object).path);

        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(context, getMainLooper(), null);
        mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        System.out.println("SOME1 DISCOEVERED");
                    }

                    @Override
                    public void onFailure(int reasonCode) {
                        System.out.println("failed discover");
                    }
                });
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncTask = new FileServerAsyncTask(context, txtConnected, object);
                checkPermissions();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
        System.out.println("resumed");
    }

    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
        System.out.println("paused");
    }


    public void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            System.out.println("PERMISSION GRANTED");
            // Should we show an explanation?
//            if (shouldShowRequestPermissionRationale(
//                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                // Explain to the user why we need to read the contacts
//            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    50);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique
        } else {
            asyncTask.execute();
            System.out.println("permissions already exist");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        asyncTask.execute();
        System.out.println("permissions requested");
    }
}
