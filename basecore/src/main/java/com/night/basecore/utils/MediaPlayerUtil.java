package com.night.basecore.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.ImageView;

import com.night.basecore.R;

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

    public static void playHornAnimation(Context context,ImageView imageView, String mp3Url) {
        imageView.setImageResource(R.drawable.horn_play_animation);
        AnimationDrawable animation = (AnimationDrawable) imageView.getDrawable();
        animation.setOneShot(true);
        animation.start();
        if (!StringUtil.isEmpty(mp3Url)) {
            MediaPlayerUtil.play(context, mp3Url);
        }
    }
}
