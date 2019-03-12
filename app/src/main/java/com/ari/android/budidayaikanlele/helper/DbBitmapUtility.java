//package com.ari.android.budidayaikanlele.helper;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//
//import java.io.ByteArrayOutputStream;
//
///**
// * Created by David on 10/10/2017.
// */
//
//public class DbBitmapUtility {
//
//    // convert from bitmap to byte array
//    public static byte[] getBytes(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
//        return stream.toByteArray();
//    }
//
//    // convert from byte array to bitmap
//    public static Bitmap getImage(byte[] image) {
//        return BitmapFactory.decodeByteArray(image, 0, image.length);
//    }
//}
