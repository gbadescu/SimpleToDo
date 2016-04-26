package com.example.gabrielbadescu.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        EditText editText = (EditText) findViewById(R.id.editText);

        editText.setText(getIntent().getStringExtra("itemName"));

        int position = editText.length();
        editText.setSelection(position);

        pos = getIntent().getIntExtra("pos",-1);


    }

    public void onEditItem(View view)
    {
        EditText etEditItem = (EditText) findViewById(R.id.editText);
        String itemText = etEditItem.getText().toString();

        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("itemChangedName", itemText);
        data.putExtra("pos",pos);
        data.putExtra("code", 200); // ints work too

        if (getParent() == null) {
            setResult(Activity.RESULT_OK, data);
        } else {
            getParent().setResult(Activity.RESULT_OK, data);
        } // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
