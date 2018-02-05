package com.yangxuqiang.customview.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.yangxuqiang.customview.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class AddPackage extends Activity {

    String[] packages = {
            "com.eg.android.AlipayGphone",
            "com.tencent.mm",
            "com.emicnet.emicall",
            "com.deppon.pdam.host.mian",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0; i < packages.length; i++){
            String package_name = packages[i];
            Log.e("ygl", i+"");
            if(isWhiteList(package_name))
            {
                Toast.makeText(getApplicationContext(),package_name+"白名单已存在！",Toast.LENGTH_SHORT).show();
            }else{
                method3("/data/apkins/package_ins_cfg", package_name);
                Toast.makeText(getApplicationContext(), package_name+" 追加完成！", Toast.LENGTH_SHORT).show();
            }
        }
        execShellCmd("pm uninstall com.gotop.write_package");
    }

    private Boolean isWhiteList(String pkg) {
        File file = new File("/data/apkins/package_ins_cfg");
        BufferedReader reader = null;
        Log.e("ygl", "isWhiteList pkg: " + pkg);
        if (!file.exists()) {
            Log.e("ygl", "isWhiteList /data/apkins/package_ins_cfg does not exists");
            return false;
        }
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                Log.e("ygl", "tempString: " + tempString);
                if (("".equals(tempString) == false)&&(pkg.contains(tempString))) {
                    return true;
                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return false;
    }
    private void execShellCmd(String cmd) {

        try {
            Process process = Runtime.getRuntime().exec("sh");
            OutputStream outputStream = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(
                    outputStream);
            dataOutputStream.writeBytes(cmd);
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    public static void method3(String fileName, String content) {
        try {

            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.writeBytes("\n");
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
