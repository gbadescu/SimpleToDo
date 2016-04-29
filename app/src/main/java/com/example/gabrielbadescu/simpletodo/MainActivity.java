package com.example.gabrielbadescu.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    ArrayAdapter<String> itemAdapter;
    ListView lvItems;
    private final int REQUEST_CODE = 20;
    private Realm realm;
    private RealmConfiguration realmConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<>();
        readItems();
        itemAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        lvItems.setAdapter(itemAdapter);

        // Create the Realm configuration
        realmConfig = new RealmConfiguration.Builder(this).build();
        // Open the Realm for the UI thread.
        realm = Realm.getInstance(realmConfig);

        setupListViewListener();
    }

    private boolean setupListViewListener()
    {
        lvItems.setOnItemLongClickListener(

               new AdapterView.OnItemLongClickListener() {

                   public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                       items.remove(pos);
                       itemAdapter.notifyDataSetChanged();
                       writeItems();
                       return true;
                   }

               }
        );

        lvItems.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {



                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {



                        Intent editIntent = new Intent(MainActivity.this,EditItemActivity.class);

                        editIntent.putExtra("itemName", items.get(pos));
                        editIntent.putExtra("pos", pos);



                        startActivityForResult(editIntent,REQUEST_CODE);

                    }



                }
        );

        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String itemName = data.getExtras().getString("itemChangedName");
            int code = data.getExtras().getInt("code");
            int pos = data.getExtras().getInt("pos");


            items.set(pos,itemName);
            itemAdapter.notifyDataSetChanged();
            writeItems();

            // Toast the name to display temporarily on screen
            Toast.makeText(this, itemName, Toast.LENGTH_SHORT).show();
        }
    }

    private void readItems()
    {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir,"todo.txt");

        try
        {
             items = new ArrayList<String>(FileUtils.readLines(todoFile));
        }
        catch(IOException exception)
        {
             items = new ArrayList<String>();
        }

    }

    private void writeItems()
    {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir,"todo.txt");

        try
        {
            FileUtils.writeLines(todoFile, items);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void onAddItem(View v)
    {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemAdapter.add(itemText);
        etNewItem.setText("");

        realm.beginTransaction();

        // Add a person
        Item item = realm.createObject(Item.class);
        item.setRemoteId(1);
        item.setName(itemText);

        // When the transaction is committed, all changes a synced to disk.
        realm.commitTransaction();


        long count  = realm.where(Item.class).count();

        String s = ""+count;

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();


    }
}
