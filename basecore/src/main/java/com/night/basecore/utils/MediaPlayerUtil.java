package com.night.basecore.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class MediaPlayerUtil {
    private static MediaPlayer mediaPlayer = new MediaPlayer();

    public static void play(Context context,String url){
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(context, Uri.parse(url));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
