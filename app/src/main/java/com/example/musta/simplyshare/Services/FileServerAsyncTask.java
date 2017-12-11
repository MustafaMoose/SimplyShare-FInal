package com.example.musta.simplyshare.Services;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.example.musta.simplyshare.ApplicationTab.ApplicationModel;
import com.example.musta.simplyshare.FilesTab.FileModel;
import com.example.musta.simplyshare.Model.Music;
import com.example.musta.simplyshare.MusicTab.MusicModel;
import com.example.musta.simplyshare.PicturesTab.PictureModel;
import com.example.musta.simplyshare.VideosTab.VideoModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by MA_Laptop on 11/5/2017.
 */
public class FileServerAsyncTask extends AsyncTask {

    private Context context;
    private TextView statusText;
    private Object sendingObject;
    ApplicationModel applicationModel;
    FileModel fileModel;
    MusicModel musicModel;
    PictureModel pictureModel;
    VideoModel videoModel;


    public FileServerAsyncTask(Context context, View statusText, Object object) {
        this.context = context;
        this.statusText = (TextView) statusText;
        this.sendingObject = object;
        ((TextView) statusText).setText("inside server async");
    }

    /**
     * Start activity that can handle the JPEG image
     */
    protected void onPostExecute(String result) {
        if (result != null) {
            statusText.setText("File copied - " + result);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse("file://" + result), "image/*");
            context.startActivity(intent);
        }
    }

    @Override
    protected String doInBackground(Object[] params) {
        try {

            /**
             * Create a server socket and wait for client connections. This
             * call blocks until a connection is accepted from a client
             */
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("host add is " + serverSocket.getInetAddress().getHostAddress());
            System.out.println("serve socket has is ready");
            Socket client = serverSocket.accept();
            System.out.println("client has connected");
            /**
             * If this code is reached, a client has connected and transferred data
             * Save the input stream from the client as a JPEG file
             */
//            final File f = new File(Environment.getExternalStorageDirectory() + "/"
//                    + context.getPackageName() + "/wifip2pshared-" + System.currentTimeMillis()
//                    + ".jpg");
//            File root = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+File.separator+"newstuff");
            File f = null;
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            if (sendingObject.getClass() == ApplicationModel.class) {
                applicationModel = (ApplicationModel) sendingObject;
                f = new File(applicationModel.path);
                FileInputStream fileInputStream = new FileInputStream(f);
                byte fileContent[] = readFile(fileInputStream, (int) f.length());
                System.out.println("file content read");
                applicationModel.data = fileContent;
                objectOutputStream.writeObject(applicationModel);
                System.out.println(applicationModel.packageName);
            } else if (sendingObject.getClass() == FileModel.class) {
                fileModel = (FileModel) sendingObject;
                f = new File(fileModel.path);
                FileInputStream fileInputStream = new FileInputStream(f);
                byte fileContent[] = readFile(fileInputStream, (int) f.length());
                System.out.println("file content read");
                fileModel.data = fileContent;
                objectOutputStream.writeObject(fileModel);
                System.out.println(fileModel.name);
            } else if (sendingObject.getClass() == MusicModel.class) {
                musicModel = (MusicModel) sendingObject;
                f = new File(musicModel.path);
                FileInputStream fileInputStream = new FileInputStream(f);
                byte fileContent[] = readFile(fileInputStream, (int) f.length());
                System.out.println("file content read");
                musicModel.data = fileContent;
                objectOutputStream.writeObject(musicModel);
                System.out.println(musicModel.name);
            } else if (sendingObject.getClass() == PictureModel.class) {
                pictureModel = (PictureModel) sendingObject;
                f = new File(pictureModel.path);FileInputStream fileInputStream = new FileInputStream(f);
                byte fileContent[] = readFile(fileInputStream, (int) f.length());
                System.out.println("file content read");
                pictureModel.data = fileContent;
                objectOutputStream.writeObject(pictureModel);
                System.out.println(pictureModel.name);
            } else if (sendingObject.getClass() == VideoModel.class) {
                videoModel = (VideoModel) sendingObject;
                f = new File(videoModel.path);
                FileInputStream fileInputStream = new FileInputStream(f);
                byte fileContent[] = readFile(fileInputStream, (int) f.length());
                System.out.println("file content read");
                videoModel.data = fileContent;
                objectOutputStream.writeObject(videoModel);
                System.out.println(videoModel.name);
            }
//            File root = new File()
//            if (!root.exists())
//            {
//                root.mkdirs();
//            }
//            File f = new File(root, "eth.jpg");
//            OutputStream outputStream = client.getOutputStream();
//            FileInputStream fileInputStream = new FileInputStream(f);
//            byte fileContent[] = readFile(fileInputStream, (int) f.length());
//            System.out.println("file content read");
//            PictureModel pictureModel = new PictureModel();
//            pictureModel.data = fileContent;
//            objectOutputStream.writeObject(pictureModel);
            objectOutputStream.flush();
            objectOutputStream.close();
            serverSocket.close();
            System.out.println("server socket closed");
            return f.getAbsolutePath();
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public byte[] readFile(FileInputStream fileInputStream, int fileLength) {
        byte fileContent[] = new byte[fileLength];
        try {
            fileInputStream.read(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }


}
