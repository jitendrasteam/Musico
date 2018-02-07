package com.musico.jitcodez.musico.Fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import com.musico.jitcodez.musico.R


/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {
    var myActivity: Activity? = null
    var shakeSwitch: Switch? = null

    object Statified {
        var MY_PREF = "ShakeFeature"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(R.layout.fragment_settings, container, false)

        shakeSwitch = view?.findViewById(R.id.switchShake)
        return view;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)

        myActivity = activity
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        myActivity = context as Activity

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val prefs = myActivity?.getSharedPreferences(Statified.MY_PREF, Context.MODE_PRIVATE)
        val isAllowed = prefs?.getBoolean("feature", false)
        if (isAllowed as Boolean) {
            shakeSwitch?.isChecked = true;
        } else {
            shakeSwitch?.isChecked = false;

        }
        shakeSwitch?.setOnCheckedChangeListener({ buttonView: CompoundButton?, b ->
            if (b) {
                val editor = myActivity?.getSharedPreferences(Statified.MY_PREF, Context.MODE_PRIVATE)?.edit()
                editor?.putBoolean("feature", true)
                editor?.apply()
            } else {
                val editor = myActivity?.getSharedPreferences(Statified.MY_PREF, Context.MODE_PRIVATE)?.edit()
                editor?.putBoolean("feature", false)
                editor?.apply()
            }
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
    }

}// Required empty public constructor
