package com.example.assignment2;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static com.example.assignment2.FolderUtil.generateFolder;
import static com.example.assignment2.FolderUtil.getAllFolders;
import static com.example.assignment2.FolderUtil.getFileRoot;
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
    public void createFolderTest(){
        String folderName1 = "test1";
        String folderName2 = "test2";
        String folderName3 = "test3";
        String folderName4 = "test4";
        String folderName5 = "test4/tt";

        generateFolder(appContext,folderName1);
        String[] result1 = getAllFolders(appContext);
        List<String> list = Arrays.asList(result1);

        assertEquals(1,result1.length);
        assertTrue(list.contains(folderName1));

        generateFolder(appContext,folderName2);
        generateFolder(appContext,folderName3);
        String[] result2 = getAllFolders(appContext);
        list = Arrays.asList(result2);
        assertEquals(3,result2.length);
        assertTrue(list.contains(folderName1));
        assertTrue(list.contains(folderName2));
        assertTrue(list.contains(folderName3));

        generateFolder(appContext,folderName2);
        generateFolder(appContext,folderName4);
        generateFolder(appContext,folderName5);
        String[] result3 = getAllFolders(appContext);
        list = Arrays.asList(result3);
        assertEquals(4,result3.length);
        assertTrue(list.contains(folderName1));
        assertTrue(list.contains(folderName2));
        assertTrue(list.contains(folderName3));
        assertTrue(list.contains(folderName4));

    }

    @Test
    public void testgetFileRoot(){
        String result = getFileRoot(appContext);
        String expect = "/storage/emulated/0/Android/data/" + appContext.getPackageName() +"/files";
        assertEquals(expect,result);
    }
}
