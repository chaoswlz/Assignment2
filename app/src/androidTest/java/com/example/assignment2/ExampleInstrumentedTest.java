package com.example.assignment2;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static com.example.assignment2.FolderUtil.generateFolder;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    @Test
    public void useAppContext() {
        // Context of the app under test.
        assertEquals("com.example.assignment2", appContext.getPackageName());
    }

    @Test
    public void testGenerateFolder(){
        String testFolder1 = "t1";
        String testFolder2 = "t2";
        String testFolder3 = "t3";

        generateFolder(appContext,testFolder1);
        generateFolder(appContext,testFolder2);

        String[] folders = FolderUtil.getAllFolders(appContext);

        assertTrue(isInArray(folders,testFolder1));
        assertTrue(isInArray(folders,testFolder2));
        assertFalse(isInArray(folders,testFolder3));

        generateFolder(appContext,testFolder3);
        assertTrue(isInArray(folders,testFolder3));



    }

    public boolean isInArray(String[] array,String item){
        for(int i = 0; i<array.length;i++){
            if (array[i] == item){
                return true;
            }
        }

        return false;
    }
}
