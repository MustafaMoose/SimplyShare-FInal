package com.example.musta.simplyshare;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.musta.simplyshare.Services.FileClientAsyncTask;
import com.example.musta.simplyshare.Services.FileServerAsyncTask;
import com.example.musta.simplyshare.Services.WiFiDirectBroadcastReceiver;

public class ReceiveFiles extends AppCompatActivity {
    Button btnReceiveSend, btnReceiveConnect;
    TextView txtReceiveConnected;
    Context context;
    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private WiFiDirectBroadcastReceiver mReceiver;
    private IntentFilter mIntentFilter;
    String ipAddress;
    private FileClientAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Creates a back button
//        fileClientAsyncTask = new FileClientAsyncTask();
        btnReceiveSend = (Button) findViewById(R.id.btnReceiveSend);
        txtReceiveConnected = (TextView) findViewById(R.id.txtReceiveConnected);
        btnReceiveConnect = (Button) findViewById(R.id.btnReceiveConnect);
        context = getApplicationContext();
        // GET IP


        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(context, getMainLooper(), null);
        mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setTitle("Enter sender IP address");

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ipAddress = input.getText().toString();
//                while(validIP(ipAddress)){
//                    builder.show();
//                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

        btnReceiveConnect.setOnClickListener(new View.OnClickListener() {
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

        btnReceiveSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncTask = new FileClientAsyncTask();
                checkPermissions();
            }
        });
    }

    //Activates the back button function
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    public void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            System.out.println("PERMISSION GRANTED");
            // Should we show an explanation?
//            if (shouldShowRequestPermissionRationale(
//                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                // Explain to the user why we need to read the contacts
//            }
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    10);
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    50);

        } else {
            asyncTask.execute(context, ipAddress);
            System.out.println("permissions already exist");
        }

        // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
        // app-defined int constant that should be quite unique
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 50: {
                asyncTask.execute(context, ipAddress);
                System.out.println("permissions requested");
            }
        }
    }

    public static boolean validIP (String ip) {
        try {
            if ( ip == null || ip.isEmpty() ) {
                return false;
            }

            String[] parts = ip.split( "\\." );
            if ( parts.length != 4 ) {
                return false;
            }

            for ( String s : parts ) {
                int i = Integer.parseInt( s );
                if ( (i < 0) || (i > 255) ) {
                    return false;
                }
            }
            if ( ip.endsWith(".") ) {
                return false;
            }

            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
