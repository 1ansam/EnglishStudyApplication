package com.night.basecore.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class MediaPlayerUtil {
    private static MediaPlayer staticMediaPlayer = new MediaPlayer();

    public static void play(Context context, String url) {
        staticMediaPlayer.reset();
        try {
            staticMediaPlayer.setDataSource(context, Uri.parse(url));
            staticMediaPlayer.prepare();
            staticMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
