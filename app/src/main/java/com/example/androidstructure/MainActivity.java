package com.example.androidstructure;

import android.Manifest.permission;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiConfiguration.KeyMgmt;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.library1api.ILibrary1Service;
import com.example.library1impl.Library1Service;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ILibrary1Service library1Service = new Library1Service();
        Log.i(TAG, library1Service.getServiceName());
        testWifi(this);
    }

    private void testWifi(Activity activity) {
        WifiManager mWifiManager = (WifiManager) activity.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = mWifiManager.getConnectionInfo();

        // 得到配置好的网络连接
        if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.i(TAG, "no permission");
            return;
        }

        mWifiManager.startScan();
        List<WifiConfiguration> wifiConfigList = mWifiManager.getConfiguredNetworks();

        Log.i(TAG, "wifiConfigList: " + wifiConfigList);

        for (WifiConfiguration wifiConfiguration : wifiConfigList) {
            //配置过的SSID
            String configSSid = wifiConfiguration.SSID;
            configSSid = configSSid.replace("\"", "");

            //当前连接SSID
            String currentSSid = info.getSSID();
            currentSSid = currentSSid.replace("\"", "");

            //比较networkId，防止配置网络保存相同的SSID
            if (currentSSid.equals(configSSid) && info.getNetworkId() == wifiConfiguration.networkId) {
                Log.e(TAG, "当前网络安全性：" + getSecurity(wifiConfiguration));
            }
        }
    }

    /**
     * These values are matched in string arrays -- changes must be kept in sync
     */
    static final int SECURITY_NONE = 0;
    static final int SECURITY_WEP = 1;
    static final int SECURITY_PSK = 2;
    static final int SECURITY_EAP = 3;

    static int getSecurity(WifiConfiguration config) {
        if (config.allowedKeyManagement.get(KeyMgmt.WPA_PSK)) {
            return SECURITY_PSK;
        }
        if (config.allowedKeyManagement.get(KeyMgmt.WPA_EAP) || config.allowedKeyManagement.get(KeyMgmt.IEEE8021X)) {
            return SECURITY_EAP;
        }
        return (config.wepKeys[0] != null) ? SECURITY_WEP : SECURITY_NONE;
    }
}