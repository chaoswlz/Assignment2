package com.example.assignment2;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.yzq.zxinglibrary.encode.CodeCreator;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    Context mMockContext;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGenerateAndReadQRcode(){
        String test1 = "11111";
        String test2 = "akdja";
        String test3 = "2019/11/11";
        String test4 = "http:www.google.com";

        Bitmap bitmap1 = CodeCreator.createQRCode(test1, 400, 400, null);
        Bitmap bitmap2 = CodeCreator.createQRCode(test2, 400, 400, null);
        Bitmap bitmap3 = CodeCreator.createQRCode(test3, 400, 400, null);
        Bitmap bitmap4 = CodeCreator.createQRCode(test4, 400, 400, null);

        Result result1 = parseInfoFromBitmap(bitmap1);
        Result result2 = parseInfoFromBitmap(bitmap2);
        Result result3 = parseInfoFromBitmap(bitmap3);
        Result result4 = parseInfoFromBitmap(bitmap4);

        assertEquals(test1,result1.getText());
        assertEquals(test2,result2.getText());
        assertEquals(test3,result3.getText());
        assertEquals(test4,result4.getText());


    }

    public Result parseInfoFromBitmap(Bitmap bitmap) {
        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        RGBLuminanceSource source = new RGBLuminanceSource(bitmap.getWidth(),
                bitmap.getHeight(), pixels);
        GlobalHistogramBinarizer binarizer = new GlobalHistogramBinarizer(source);
        BinaryBitmap image = new BinaryBitmap(binarizer);
        Result result = null;
        try {
            result = new QRCodeReader().decode(image);
            return result;
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ChecksumException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }

        return null;

    }



}