/*
 *  LeEco Extras Settings Module for Resurrection Remix ROMs
 *  Made by @andr68rus 2017
 */

package org.omnirom.device;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;
import android.os.SystemProperties;
import java.io.*;
import android.widget.Toast;  
import android.preference.ListPreference;

import org.omnirom.device.R;

public class DeviceSettings extends PreferenceActivity implements OnPreferenceChangeListener {
	private static final boolean DEBUG = false;
	private static final String TAG = "LePref";
	private static final String AKT_KEY = "akt";
	private static final String AKT_SYSTEM_PROPERTY = "persist.AKT.profile";

	private static final String QC_SYSTEM_PROPERTY = "persist.sys.le_fast_chrg_enable";
	private static final String HAL3_SYSTEM_PROPERTY = "persist.camera.HAL3.enabled";
	private static final String GPS_THROTTLE_SYSTEM_PROPERTY = "persist.ps.gpsthrottle";

    	private static final String SYSTEM_PROPERTY_PM_PROXIMITY_OFF = "persist.pm.proximity_off";

    	private static final String SYSTEM_PROPERTY_PM_PROXIMITY_ON = "persist.pm.proximity_on";

    	private static final String SYSTEM_PROPERTY_PM_PROXIMITY_BON = "persist.pm.proximity_bon";

    	private static final String SYSTEM_PROPERTY_PM_PROXIMITY_BOFF = "persist.pm.proximity_boff";

    	private static final String SYSTEM_PROPERTY_PM_ON_AFTER_CALL = "persist.pm.on_after_call";

    	private static final String SYSTEM_PROPERTY_PM_LOCK_INCALL = "persist.pm.lock_incall";

    	private static final String SYSTEM_PROPERTY_PM_DEEP_IDLE = "persist.pm.deep_idle";

    	private static final String SYSTEM_PROPERTY_PM_CPUIDLE_DS = "persist.pm.cpuidle_ds";

    	private static final String SYSTEM_PROPERTY_SYS_HALL_SENSOR = "persist.sys.hall_sensor";

    private static final String SYSTEM_PROPERTY_PM_READER_MODE = "persist.pm.reader_mode";

    private static final String SYSTEM_PROPERTY_PM_HIDE_GMS = "persist.pm.hide_gms";
    private static final String SYSTEM_PROPERTY_PM_FORCE_GMS = "persist.pm.force_gms";

    private static final String SYSTEM_PROPERTY_IMS_ENABLED = "persist.ims.enabled";

    private static final String SYSTEM_PROPERTY_CAMERA_FOCUS_FIX = "persist.camera.focus_fix";

    private static final String SYSTEM_PROPERTY_PM_KRNL_WL_BLOCK = "persist.pm.krnl_wl_block";
    private static final String SYSTEM_PROPERTY_PM_KRNL_WL_QCOM_RX = "persist.pm.krnl_wl_qcom_rx";

    private static final String SYSTEM_PROPERTY_PM_STOP_SVC = "persist.pm.stop_svc";

	private SwitchPreference mEnableQC;
	private SwitchPreference mEnableHAL3;
	private SwitchPreference mEnableGPSThrottle;
	private ListPreference mAKT;

	private SwitchPreference mProximityOff;
	private SwitchPreference mProximityOn;
	private SwitchPreference mProximityBOn;
	private SwitchPreference mProximityBOff;
	private SwitchPreference mOnAfterCall;
	private SwitchPreference mLockIncallCall;

	private SwitchPreference mHallSensor;
	private SwitchPreference mDeepIdle;

	private SwitchPreference mCpuidleDeepestState;

	private SwitchPreference mReaderMode;

	private SwitchPreference mHideGMS;
	private SwitchPreference mForceGMS;

	private SwitchPreference mImsEnabled;

	private SwitchPreference mCameraFocusFix;

	private SwitchPreference mKrnlWlBlock;
	private SwitchPreference mKrnlWlQcomRX;
	private SwitchPreference mStopSvc;
	
    private Context mContext;
    private SharedPreferences mPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.le_settings);
        mContext = getApplicationContext();
        
        mEnableQC = (SwitchPreference) findPreference(QC_SYSTEM_PROPERTY);
        if( mEnableQC != null ) {
            mEnableQC.setChecked(SystemProperties.getBoolean(QC_SYSTEM_PROPERTY, false));
            mEnableQC.setOnPreferenceChangeListener(this);
        }
                
        mEnableHAL3 = (SwitchPreference) findPreference(HAL3_SYSTEM_PROPERTY);
        if( mEnableHAL3 != null ) {
            mEnableHAL3.setChecked(SystemProperties.getBoolean(HAL3_SYSTEM_PROPERTY, false));
            mEnableHAL3.setOnPreferenceChangeListener(this);
        }

        mEnableGPSThrottle = (SwitchPreference) findPreference(GPS_THROTTLE_SYSTEM_PROPERTY);
        if( mEnableGPSThrottle != null ) {
            mEnableGPSThrottle.setChecked(SystemProperties.getBoolean(GPS_THROTTLE_SYSTEM_PROPERTY, false));
            mEnableGPSThrottle.setOnPreferenceChangeListener(this);
        }


        mProximityOff = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_PROXIMITY_OFF);
        if( mProximityOff != null ) {
            mProximityOff.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_PROXIMITY_OFF, false));
            mProximityOff.setOnPreferenceChangeListener(this);
        }

        mProximityOn = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_PROXIMITY_ON);
        if( mProximityOn != null ) {
            mProximityOn.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_PROXIMITY_ON, false));
            mProximityOn.setOnPreferenceChangeListener(this);
        }

        mProximityBOn = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_PROXIMITY_BON);
        if( mProximityBOn != null ) {
            mProximityBOn.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_PROXIMITY_BON, false));
            mProximityBOn.setOnPreferenceChangeListener(this);
        }


        mProximityBOff = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_PROXIMITY_BOFF);
        if( mProximityBOff != null ) {
            mProximityBOff.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_PROXIMITY_BOFF, false));
            mProximityBOff.setOnPreferenceChangeListener(this);
        }

        mOnAfterCall = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_ON_AFTER_CALL);
        if( mOnAfterCall != null ) {
            mOnAfterCall.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_ON_AFTER_CALL, false));
            mOnAfterCall.setOnPreferenceChangeListener(this);
        }


        mLockIncallCall = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_LOCK_INCALL);
        if( mLockIncallCall != null ) {
            mLockIncallCall.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_LOCK_INCALL, false));
            mLockIncallCall.setOnPreferenceChangeListener(this);
        }

        mHallSensor = (SwitchPreference) findPreference(SYSTEM_PROPERTY_SYS_HALL_SENSOR);
        if( mHallSensor != null ) {
            mHallSensor.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_SYS_HALL_SENSOR, false));
            mHallSensor.setOnPreferenceChangeListener(this);
        }

        mDeepIdle = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_DEEP_IDLE);
        if( mDeepIdle != null ) {
            mDeepIdle.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_DEEP_IDLE, false));
            mDeepIdle.setOnPreferenceChangeListener(this);
        }
        
        mCpuidleDeepestState = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_CPUIDLE_DS);
        if( mCpuidleDeepestState != null ) {
            mCpuidleDeepestState.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_CPUIDLE_DS, false));
            mCpuidleDeepestState.setOnPreferenceChangeListener(this);
        }

        mReaderMode = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_READER_MODE);
        if( mReaderMode != null ) {
            mReaderMode.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_READER_MODE, false));
            mReaderMode.setOnPreferenceChangeListener(this);
        }


        mHideGMS = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_HIDE_GMS);
        if( mHideGMS != null ) {
            mHideGMS.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_HIDE_GMS, false));
            mHideGMS.setOnPreferenceChangeListener(this);
        }

        mForceGMS = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_FORCE_GMS);
        if( mForceGMS != null ) {
            mForceGMS.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_FORCE_GMS, false));
            mForceGMS.setOnPreferenceChangeListener(this);
        }


        mImsEnabled = (SwitchPreference) findPreference(SYSTEM_PROPERTY_IMS_ENABLED);
        if( mImsEnabled != null ) {
            mImsEnabled.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_IMS_ENABLED, false));
            mImsEnabled.setOnPreferenceChangeListener(this);
        }

        mCameraFocusFix = (SwitchPreference) findPreference(SYSTEM_PROPERTY_CAMERA_FOCUS_FIX);
        if( mCameraFocusFix != null ) {
            mCameraFocusFix.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_CAMERA_FOCUS_FIX, false));
            mCameraFocusFix.setOnPreferenceChangeListener(this);
        }



        mKrnlWlBlock = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_KRNL_WL_BLOCK);
        if( mKrnlWlBlock != null ) {
            mKrnlWlBlock.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_KRNL_WL_BLOCK, false));
            mKrnlWlBlock.setOnPreferenceChangeListener(this);
        }

        mKrnlWlQcomRX = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_KRNL_WL_QCOM_RX);
        if( mKrnlWlQcomRX != null ) {
            mKrnlWlQcomRX.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_KRNL_WL_QCOM_RX, false));
            mKrnlWlQcomRX.setOnPreferenceChangeListener(this);
        }

        mStopSvc = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_STOP_SVC);
        if( mStopSvc != null ) {
            mStopSvc.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_STOP_SVC, false));
            mStopSvc.setOnPreferenceChangeListener(this);
        }



        /*
        mAKT = (ListPreference) findPreference(AKT_KEY);
        if( mAKT != null ) {
            mAKT.setValue(SystemProperties.get(AKT_SYSTEM_PROPERTY, "Stock"));
            mAKT.setOnPreferenceChangeListener(this);
        }*/
        
        if (DEBUG) Log.d(TAG, "Initializating done");
    }

    // Set AKT
    private void setAKT(String value) {
		try {
			Process su = Runtime.getRuntime().exec("su");
			DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
			outputStream.writeBytes("mount -o remount,rw /system\n");
			outputStream.writeBytes("cat /system/etc/lepref/AKT/" + value + " > /system/etc/init.d/99AKT\n");
			outputStream.writeBytes("chmod 777 /system/etc/init.d/99AKT\n");
			outputStream.writeBytes("/system/etc/init.d/99AKT\n");
			outputStream.writeBytes("mount -o remount,ro /system\n");
			outputStream.flush();
			outputStream.writeBytes("exit\n");
			outputStream.flush();
			su.waitFor();
		} catch(IOException e){
			Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
			toast.show();
		} catch(InterruptedException e){
			Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
			toast.show();
		}
		SystemProperties.set(AKT_SYSTEM_PROPERTY, value);
    }
   
    private void setEnable(String key, boolean value) {
	if(value) {
		SystemProperties.set(key, "1");
	} else {
			SystemProperties.set(key, "0");
	}
	if (DEBUG) Log.d(TAG, key + " setting changed");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String key = preference.getKey();
        boolean value;
        String strvalue;
        if (DEBUG) Log.d(TAG, "Preference changed.");

	if (AKT_KEY.equals(key)) {
		strvalue = (String) newValue;
		if (DEBUG) Log.d(TAG, "AKT setting changed: " + strvalue);
		setAKT(strvalue);
		return true;
	} else {
		value = (Boolean) newValue;
		((SwitchPreference)preference).setChecked(value);
		setEnable(key,value);
		return true;
	} 
    }
}
