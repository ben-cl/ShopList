package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.grocerystore.data.DatabaseHandler;
import com.example.grocerystore.model.Item;
import com.example.grocerystore.model.Store;

public class EditItem extends AppCompatActivity {

    private EditText nameEditField;
    private Button editItem;
    private Button backMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        final DatabaseHandler db = new DatabaseHandler(EditItem.this);

        nameEditField = findViewById(R.id.nameEditFieldItem);
        editItem = findViewById(R.id.editItem);
        backMenu = findViewById(R.id.backMenuEditItem);


        //
        editItem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                Intent intent = getIntent();

                Item item = (Item)intent.getSerializableExtra("item");


                // Edit Store
                String name = nameEditField.getText().toString();

                item.setName(name);


                //todo
                db.editItem(item);


                // Launch back menu
                Intent intentback = new Intent(EditItem.this, ItemList.class);
                startActivity(intentback);


            }
        });

        // Back button if does not want to add any new item
        backMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intentSet = getIntent();
                Store store = (Store)intentSet.getSerializableExtra("store");

                // Launch back menu
                Intent intent = new Intent(EditItem.this, ItemList.class);
                intent.putExtra("store", store);
                startActivity(intent);

            }
        });
    }
}
