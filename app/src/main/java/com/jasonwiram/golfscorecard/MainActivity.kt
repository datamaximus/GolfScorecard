package com.jasonwiram.golfscorecard

import android.app.ListActivity
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioGroup
import android.widget.ToggleButton

class MainActivity : ListActivity() {
    private val mHoles = arrayOfNulls<Hole>(18)
    private var mListAdapter: ListAdapter? = null
    private var mSharedPreferences: SharedPreferences? = null
    private var mEditor: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        mEditor = mSharedPreferences!!.edit()

        // Initialize holes
        var strokes = 0
        for (i in mHoles.indices) {
            strokes = mSharedPreferences!!.getInt(KEY_STROKECOUNT + i, 0)
            mHoles[i] = Hole("Hole " + (i + 1) + " :", strokes)
        }

        mListAdapter = ListAdapter(this, mHoles)
        listAdapter = mListAdapter
    }

    override fun onPause() {
        super.onPause()

        for (i in mHoles.indices) {
            mEditor!!.putInt(KEY_STROKECOUNT + i, mHoles[i]!!.getmStrokeCount())
        }
        mEditor!!.apply()
    }

    companion object {
        private val PREFS_FILE = "com.jasonwiram.golfscorecard.preferences"
        private val KEY_STROKECOUNT = "key+strokecount"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_clear_strokes -> {
                mEditor!!.clear()
                mEditor!!.apply()

                for (hole in mHoles) {
                    hole!!.setmStrokeCount(0)
                }
                mListAdapter!!.notifyDataSetChanged()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
