package com.example.gworlddinning

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.inputmethodservice.Keyboard
import android.nfc.Tag
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import java.util.ArrayList
import java.util.prefs.PreferenceChangeEvent

class MainActivity : AppCompatActivity() {

    // SharedPreferences.
    // SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
    // SharedPreferences.Editor editor = preferences.edit();

    private var editTextDistance: EditText? = null
    private var buttonFindRestaurants: Button? = null

    internal lateinit var mRestaurantTypes: Button
    internal lateinit var mTypesSelected: TextView
    internal lateinit var listItems: Array<String>
    internal lateinit var checkedItems: BooleanArray
    internal var mUserItems = ArrayList<Int>()
    private val findRestaurantsWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            if (Integer.parseInt(editTextDistance!!.text.toString()) > 0) {
                buttonFindRestaurants!!.isEnabled = !editTextDistance!!.text.toString().trim { it <= ' ' }.isEmpty()
            } else {
                buttonFindRestaurants!!.isEnabled = false
            }
        }

        override fun afterTextChanged(editable: Editable) {

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextDistance = findViewById(R.id.edit_distance)
        buttonFindRestaurants = findViewById(R.id.button_find_restaurants)

        editTextDistance!!.addTextChangedListener(findRestaurantsWatcher)

        mRestaurantTypes = findViewById<View>(R.id.btnRestaurantTypes) as Button
        mTypesSelected = findViewById<View>(R.id.tvTypesSelected) as TextView

        listItems = resources.getStringArray(R.array.restaurant_item)
        checkedItems = BooleanArray(listItems.size)

        mRestaurantTypes.setOnClickListener {
            val mBuilder = AlertDialog.Builder(this@MainActivity)
            mBuilder.setTitle(getString(R.string.dialog_title))
            mBuilder.setMultiChoiceItems(listItems, checkedItems) { dialogInterface, position, isChecked ->
                if (isChecked) {
                    if (!mUserItems.contains(position)) {
                        mUserItems.add(position)
                    } else if (mUserItems.contains(position)) {
                        mUserItems.removeAt(position)
                    }
                }
            }

            mBuilder.setCancelable(false)
            mBuilder.setPositiveButton(getString(R.string.ok_label)) { dialogInterface, which ->
                var item = ""
                for (i in mUserItems.indices) {
                    item = item + listItems[mUserItems[i]]
                    if (i != mUserItems.size - 1) {
                        item = "$item,"
                    }
                }
                mTypesSelected.text = item
            }

            val builder = mBuilder.setNegativeButton(getString(R.string.dismiss_label)) { dialogInterface, i -> dialogInterface.dismiss() }

            val builder1 = mBuilder.setNeutralButton(getString(R.string.clear_all_label)) { dialogInterface, which ->
                for (i in checkedItems.indices) {
                    checkedItems[i] = false
                    mUserItems.clear()
                    mTypesSelected.text = ""
                }
            }

            val mDialog = mBuilder.create()
            mDialog.show()
        }
    }

    private fun storeDistance(distance: Int) {
        val mSharedPreferences = getSharedPreferences("edit_distance", MODE_PRIVATE)
        val mEditor = mSharedPreferences.edit()
        mEditor.putInt("distance", distance)
        mEditor.apply()
    }

    // Get distance function here.
    //    private int getDistance(){
    //        SharedPreferences mSharedPreferences = getSharedPreferences("edit_distance", MODE_PRIVATE);
    //        int selectedDistance = mSharedPreferences.getInt("distance",getResources(),getDistance(R.distance.edit_distance));
    //        return selectedDistance;
    //    }

}


