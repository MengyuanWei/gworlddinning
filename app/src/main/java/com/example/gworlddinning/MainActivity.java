package com.example.gworlddinning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity {

    // SharedPreferences.
    // SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
    // SharedPreferences.Editor editor = preferences.edit();

    private EditText editTextDistance;
    private Button buttonFindRestaurants;

    Button mRestaurantTypes;
    TextView mTypesSelected;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDistance = findViewById(R.id.edit_distance);
        buttonFindRestaurants = findViewById(R.id.button_find_restaurants);

        editTextDistance.addTextChangedListener(findRestaurantsWatcher);

        mRestaurantTypes = (Button) findViewById(R.id.btnRestaurantTypes);
        mTypesSelected = (TextView) findViewById(R.id.tvTypesSelected);

        listItems = getResources().getStringArray(R.array.restaurant_item);
        checkedItems = new boolean[listItems.length];

        mRestaurantTypes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                mBuilder.setTitle(getString(R.string.dialog_title));
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            if(!mUserItems.contains(position)){
                                mUserItems.add(position);
                            } else if(mUserItems.contains(position)){
                                mUserItems.remove(position);
                            }
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(getString(R.string.ok_label), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++){
                            item = item + listItems[mUserItems.get(i)];
                            if(i != mUserItems.size() - 1){
                                item = item + ",";
                            }
                        }
                        mTypesSelected.setText(item);
                    }
                });

                AlertDialog.Builder builder = mBuilder.setNegativeButton(getString(R.string.dismiss_label), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog.Builder builder1 = mBuilder.setNeutralButton(getString(R.string.clear_all_label), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                            mUserItems.clear();
                            mTypesSelected.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
    }
    private TextWatcher findRestaurantsWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (Integer.parseInt(editTextDistance.getText().toString()) > 0 ) {
                buttonFindRestaurants.setEnabled(!editTextDistance.getText().toString().trim().isEmpty());
            }
            else {
                buttonFindRestaurants.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void storeDistance(int distance){
        SharedPreferences mSharedPreferences = getSharedPreferences("edit_distance", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("distance", distance);
        mEditor.apply();
    }

    // Get distance function here.
//    private int getDistance(){
//        SharedPreferences mSharedPreferences = getSharedPreferences("edit_distance", MODE_PRIVATE);
//        int selectedDistance = mSharedPreferences.getInt("distance",getResources(),getDistance(R.distance.edit_distance));
//        return selectedDistance;
//    }

}


