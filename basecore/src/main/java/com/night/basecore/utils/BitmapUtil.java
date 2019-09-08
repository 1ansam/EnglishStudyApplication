package com.night.basecore.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BitmapUtil {

    //bitmap->byte[]
    public static byte[] bmp2ByteArr(Bitmap bmp) {
        if (bmp == null || bmp.isRecycled())
            return null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    //byte[]->bitmap
    public static Bitmap byteArr2Bmp(byte[] b) {
        if (b == null)
            return null;
        Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        return bitmap;
    }

    //Drawable->Bitmap
    public static Bitmap draw2Bmp(Drawable drawable) {
        if (drawable == null)
            return null;
        BitmapDrawable bd = (BitmapDrawable) drawable;
        return bd.getBitmap();
    }

    //Bitmap->Drawable
    public static Drawable bmp2Draw(Context context, Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled())
            return null;
        return new BitmapDrawable(context.getResources(), bitmap);
    }
}
