package com.example.musta.simplyshare.Services;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Bundle;

import java.util.Collection;
import java.util.List;

/**
 * Created by MA_Laptop on 11/27/2017.
 */

public class MyDialogClass extends DialogFragment {

    List<String> deviceNames;
    WifiP2pDevice wifidevice;
    Collection<WifiP2pDevice> peerList;
    CharSequence[] charSequence;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        CharSequence[] charSequence = deviceNames.toArray(new CharSequence[deviceNames.size()]);
        builder.setTitle("Select device").setItems(charSequence, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                        wifidevice = peerList.iterator();[which];
//                        String deviceName = charSequence[]
                System.out.println(which);
                for(WifiP2pDevice device: peerList) {
                    if(deviceNames.get(which).equals(device.deviceName)){
                        wifidevice = device;
                        System.out.println("found device");
                        mListener.onDialogPositiveClick();
                        dismiss();
                    }
                }
            }
        });
        return builder.create();

    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface MyDialogListener {
        public void onDialogPositiveClick();
    }

    // Use this instance of the interface to deliver action events
    MyDialogClass.MyDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (MyDialogClass.MyDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            System.out.println(e);
//            throw new ClassCastException(context.toString()
//                    + " must implement NoticeDialogListener");
        }
    }
}
