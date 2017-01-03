package com.gw150914.jabberwocky.gui;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

import com.gw150914.jabberwocky.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class InboundActivity extends Activity {

    TextView inboundUriText;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbound);

        inboundUriText = (TextView) findViewById(R.id.inbound_uri_text);

        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if(shouldAskPermissions()) {
            verifyStoragePermissions(this);
        }


        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (type.startsWith("audio/")) {
                if ("audio/mp3".equals(type) || "audio/ogg".equals(type) || "audio/opus".equals(type)) {
                    handleUnknownSingleAudio(intent);
                }
                else {
                    handleUnknownSingleAudio(intent);
                }
            }
        }
        else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("audio/")) {
                if ("audio/mp3".equals(type) || "audio/ogg".equals(type) || "audio/opus".equals(type)) {
                    handleKnownMultipleAudio(intent);
                }
                else {
                    handleUnknownMultipleAudio(intent);
                }
            }
        }
        else {
            // Handle other intents, such as being started from the home screen
        }
    }

    void handleKnownSingleAudio(Intent intent) {
        Uri soundUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (soundUri != null) {
            inboundUriText.setText("InboundUri: " + soundUri.toString());
        }
    }

    void handleUnknownSingleAudio(Intent intent) {
        Uri soundUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (soundUri != null) {
            inboundUriText.setText("InboundURI: " + soundUri.toString());

            try {
                File soundsFolder = new File(getFilesDir(), "sounds");
                if (soundsFolder.mkdir()) {
                    System.out.println("DEBUG: Folder " + soundsFolder.getAbsolutePath() + " has been created");
                }
                File outputFile = new File(soundsFolder, "inbound.opus");
                saveFile(soundUri, outputFile);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void handleKnownMultipleAudio(Intent intent) {
        ArrayList<Uri> soundUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (soundUris != null) {
            // Update UI to reflect multiple images being shared
        }
    }

    void handleUnknownMultipleAudio(Intent intent) {
        ArrayList<Uri> soundUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (soundUris != null) {
            // Update UI to reflect multiple images being shared
        }
    }

    private void saveFile(Uri sourceUri, File destination) {
        try {
            File source = new File(sourceUri.getPath());
            FileChannel src = new FileInputStream(source).getChannel();
            FileChannel dst = new FileOutputStream(destination).getChannel();
            dst.transferFrom(src, 0, src.size());
            src.close();
            dst.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @TargetApi(23)
    public static void verifyStoragePermissions(Activity activity) {

        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }
}
