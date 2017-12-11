package com.example.musta.simplyshare.Services;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import com.example.musta.simplyshare.ApplicationTab.ApplicationModel;
import com.example.musta.simplyshare.FilesTab.FileModel;
import com.example.musta.simplyshare.MusicTab.MusicModel;
import com.example.musta.simplyshare.PicturesTab.PictureModel;
import com.example.musta.simplyshare.VideosTab.VideoModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class FileClientAsyncTask extends AsyncTask {
    Context context;
    InetAddress address;
    Object receivedObject;
    ApplicationModel applicationModel;
    FileModel fileModel;
    MusicModel musicModel;
    PictureModel pictureModel;
    VideoModel videoModel;
    String ipAddress;


    @Override
    protected Object doInBackground(Object[] params) {
        context = (Context) params[0];
        ipAddress = params[1].toString();
//        ((TextView) statusText).setText("inside server async");
        connectClient();
        return null;


    }

    public void connectClient() {
        String host;
        int port;
        int len;
        Socket socket = new Socket();
        byte buf[] = new byte[1024];
        try {
            /**
             * Create a client socket with the host,
             * port, and timeout information.
             */
//            socket.bind(null);
            System.out.println("inside connectCLient()");
            socket.connect((new InetSocketAddress(ipAddress, 8888)), 5000);

            System.out.println("From client, is it connect - " + socket.isConnected());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            receivedObject = objectInputStream.readObject();
            System.out.println("object received");
            /**
             * Create a byte stream from a JPEG file and pipe it to the output stream
             * of the socket. This data will be retrieved by the server device.
             */
//            InputStream inputStream = socket.getInputStream();
            ContentResolver cr = context.getContentResolver();
//            InputStream inputStream = null;
            File root = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + "newstuff2");
            if (!root.exists()) {
                root.mkdirs();
            }
            System.out.println("got file folcation for saving");
            File f = null;
            FileOutputStream fileOutputStream = null;
            if (receivedObject.getClass() == ApplicationModel.class) {
                applicationModel = (ApplicationModel) receivedObject;
//                f = new File(root + File.separator + "Applications", applicationModel.packageName);
                f = new File(root + File.separator + "Applications", applicationModel.packageName + "." + applicationModel.ext);
                if(!f.getParentFile().exists()){
                    System.out.println("DIR doesnt existing so making one");
                    f.getParentFile().mkdirs();
                }
                f.createNewFile();
                fileOutputStream = new FileOutputStream(f);
                fileOutputStream.write(applicationModel.data);
                System.out.println(applicationModel.name);
                Intent promptInstall = new Intent(Intent.ACTION_VIEW)
                        .setDataAndType(Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + "newstuff2" + File.separator + "Applications" + File.separator + applicationModel.packageName + "." +  applicationModel.ext),
                                "application/vnd.android.package-archive");
                context.startActivity(promptInstall);
            } else if (receivedObject.getClass() == FileModel.class) {
                fileModel = (FileModel) receivedObject;
//                f = new File(root + File.separator + "Files", fileModel.name + "." + fileModel.ext);
                f = new File(root + File.separator + "Files", fileModel.name + "." + fileModel.ext);
                if(!f.getParentFile().exists()){
                    System.out.println("DIR doesnt existing so making one");
                    f.getParentFile().mkdirs();
                }
                f.createNewFile();
                fileOutputStream = new FileOutputStream(f);
                fileOutputStream.write(fileModel.data);
                System.out.println(fileModel.name);
            } else if (receivedObject.getClass() == MusicModel.class) {
                musicModel = (MusicModel) receivedObject;
//                System.out.println(musicModel.name);
//                System.out.println(musicModel.ext);
                File temp = new File(root + File.separator + "Music" );
//                if(!temp.exists()){
//                    System.out.println("noy exisit");
//                    temp.mkdirs();
//                }
                f = new File(root + File.separator + "Music", musicModel.name + "." + musicModel.ext);
                if(!f.getParentFile().exists()){
                    System.out.println("DIR doesnt existing so making one");
                    f.getParentFile().mkdirs();
                }
                f.createNewFile();
                fileOutputStream = new FileOutputStream(f);
                fileOutputStream.write(musicModel.data);
                System.out.println(musicModel.name);
            } else if (receivedObject.getClass() == PictureModel.class) {
                pictureModel = (PictureModel) receivedObject;
//                f = new File(root + File.separator + "Images", pictureModel.name + "." + pictureModel.ext);
                f = new File(root + File.separator + "Pictures", pictureModel.name + "." + pictureModel.ext);
                if(!f.getParentFile().exists()){
                    System.out.println("DIR doesnt existing so making one");
                    f.getParentFile().mkdirs();
                }
                f.createNewFile();
                fileOutputStream = new FileOutputStream(f);
                fileOutputStream.write(pictureModel.data);
                System.out.println(pictureModel.name);
            } else if (receivedObject.getClass() == VideoModel.class) {
                videoModel = (VideoModel) receivedObject;
//                f = new File(root + File.separator + "Files", videoModel.name + "." + videoModel.ext);
                f = new File(root + File.separator + "Videos", videoModel.name + "." + videoModel.ext);
                if(!f.getParentFile().exists()){
                    System.out.println("DIR doesnt existing so making one");
                    f.getParentFile().mkdirs();
                }
                f.createNewFile();
                fileOutputStream = new FileOutputStream(f);
                fileOutputStream.write(videoModel.data);
                System.out.println(videoModel.name);
            }
//            File f = new File(root, "eth.jpg");
//            f.createNewFile();
//            FileOutputStream fileOutputStream = new FileOutputStream(f);
//            fileOutputStream.write(img.data);
//            byte[] b = new byte[1024];
//            while(( len = inputStream.read(b)) != -1) {
//                fileOutputStream.write(b,0,len);
//                System.out.println(len);
//                System.out.println("somehting read");
//            }
            System.out.println("out of while");
            System.out.println("client reahed end");
            fileOutputStream.flush();
            fileOutputStream.close();
//            inputStream.close();
//            scan.close();
        } catch (StreamCorruptedException e) {
            System.out.println(e);
            e.printStackTrace();
//            System.out.println(e.getCause());
//            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e);
            //catch logic
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile() {
    }
}
