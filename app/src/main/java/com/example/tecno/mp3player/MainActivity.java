package com.example.tecno.mp3player;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String[] item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.lv);


        final ArrayList<File> myvideos = findvideos(Environment.getExternalStorageDirectory());
        item = new String[myvideos.size()];
         for (int i=0 ; i < myvideos.size() ; i++) {
             item[i] = myvideos.get(i).getName().toString()
                     .replace(".mp4","").replace(".mkv","");
         }
        ArrayAdapter<String> adb = new ArrayAdapter<String>(getApplicationContext(),R.layout.videos_layout,R.id.textView,item);
         listView.setAdapter(adb);

         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 startActivity(new Intent(getApplicationContext(),Main2Activity.class)
                         .putExtra("pos",i).putExtra("videoslist",myvideos)
                         .putExtra("username", myvideos.get(i).getName().toString().replace(".mp4","")));
             }
         });

    }
    public ArrayList<File> findvideos (File root) {
        ArrayList<File> al = new ArrayList<File>();
        File[] files = root.listFiles();
        for(File singlefile : files) {
            if(singlefile.isDirectory() && !singlefile.isHidden()) {
                al.addAll(findvideos(singlefile));
            }
            else {
                if (singlefile.getName().endsWith(".mp4") || singlefile.getName().endsWith(".mkv"))
                    al.add(singlefile);
            }
        }
        return al;
    }

}
