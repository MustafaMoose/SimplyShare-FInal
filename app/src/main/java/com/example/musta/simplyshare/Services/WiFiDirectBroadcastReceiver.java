package com.example.musta.simplyshare.Services;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.TextView;

import com.example.musta.simplyshare.R;
import com.example.musta.simplyshare.ReceiveFiles;
import com.example.musta.simplyshare.SendFiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by MA_Laptop on 11/5/2017.
 */
public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

    private ReceiveFiles receiveFilesActivity;
    TextView txtConnected;
    WifiP2pDevice wifidevice;
    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private SendFiles sendFilesActivity;
    MyDialogClass myDialogClass;
    boolean deviceFound;

    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel,
                                       SendFiles activity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.sendFilesActivity = activity;
        myDialogClass = new MyDialogClass();
    }

    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel,
                                       ReceiveFiles activity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.receiveFilesActivity = activity;
        myDialogClass = new MyDialogClass();
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        String action = intent.getAction();
        txtConnected = (TextView) ((Activity)context).findViewById(R.id.txtConnected);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        WifiP2pManager.PeerListListener myPeerListListener = new WifiP2pManager.PeerListListener() {
            @Override
            public void onPeersAvailable(WifiP2pDeviceList peers) {
                final Collection<WifiP2pDevice> peerList = peers.getDeviceList();
                final List<String> deviceNames = new ArrayList<String>();
                CharSequence[] charSequence;
                for(WifiP2pDevice device: peerList) {
                    deviceNames.add(device.deviceName);
                }
                if(!deviceFound){
                    charSequence = deviceNames.toArray(new CharSequence[deviceNames.size()]);

                    myDialogClass.deviceNames = deviceNames;
                    myDialogClass.peerList = peerList;
                    myDialogClass.charSequence = charSequence;
                    myDialogClass.mListener = new MyDialogClass.MyDialogListener() {
                        @Override
                        public void onDialogPositiveClick() {
                            wifidevice = myDialogClass.wifidevice;
                            connectDevice();
                        }
                    };
                    myDialogClass.show(((Activity)context).getFragmentManager(), "missiles");
                }
                else {
                    myDialogClass.dismiss();
                }



            }
        };

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            System.out.println("WIFI_P2P_STATE_CHANGED_ACTION");
            // Check to see if Wi-Fi is enabled and notify appropriate activity
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                // Wifi P2P is enabled
            } else {
                // Wi-Fi P2P is not enabled
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
//            System.out.println("WIFI_P2P_PEERS_CHANGED_ACTION");
            // Call WifiP2pManager.requestPeers() to get a list of current peers
            if (mManager != null) {
                mManager.requestPeers(mChannel, myPeerListListener);
            }
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            System.out.println("WIFI_P2P_CONNECTION_CHANGED_ACTION");
            // Respond to new connection or disconnections
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            System.out.println("WIFI_P2P_THIS_DEVICE_CHANGED_ACTION");
            // Respond to this device's wifi state changing
        }
    }

    public void connectDevice() {
        //obtain a peer from the WifiP2pDeviceList
        if(wifidevice != null) {
            WifiP2pConfig config = new WifiP2pConfig();
//                    txtIpAddress.setText(wifidevice.deviceAddress);

            config.deviceAddress = wifidevice.deviceAddress;

            mManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {

                @Override
                public void onSuccess() {
                    //success logic
//                            System.out.println("-------------------------------------------CONNECTED");
                    txtConnected.setText("CONNECTED");
                    System.out.println("ip address" + wifidevice.deviceAddress);
                    deviceFound = true;
                }

                @Override
                public void onFailure(int reason) {
                    //failure logic
                    System.out.println("-------------------------------------------FAILED");
                    txtConnected.setText("FAILED");
                }
            });
        }
    }
}

