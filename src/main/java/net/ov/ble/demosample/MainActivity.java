package net.ov.ble.demosample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import net.ov.orangebeacon.lib.OBGConfig;
import net.ov.orangebeacon.lib.OBGService;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mWebView = (WebView)findViewById(R.id.activity_main_webview);

        mWebView.loadUrl("file:///android_asset/www/index.html"); //Insérer ici l'adresse mail du site à afficher
        mWebView.setWebViewClient(new WebViewClient()); //interdit au navigateur de prendre la main
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //for debug only, verbose logging
        OBGService.MODE_DEBUG = false;

        //replace by your API key
        OBGConfig.remoteUser="ur-548f0071f0f5c1428908617";
        OBGConfig.remotePass="pr-548f0071f0fbb1350315161";

        // repeat more ofter the notification (default 12 hours)
        OBGConfig.minUniqueActionTimeframe = 10f;

        OBGConfig.notificationIcon = R.drawable.ic_notif;
        OBGConfig.notificationColor = getResources().getColor(R.color.main);

        OBGConfig.notificationLayout      = net.ov.orangebeacon.lib.R.layout.obg_notification;
        OBGConfig.notificationLayoutIcon  = net.ov.orangebeacon.lib.R.id.obg_notification_icon;
        OBGConfig.notificationLayoutTitle = net.ov.orangebeacon.lib.R.id.obg_notification_title;
        OBGConfig.notificationLayoutText  = net.ov.orangebeacon.lib.R.id.obg_notification_text;

        startService(new Intent(this, OBGService.class));

    }
    @Override
    public void onBackPressed(){
        if(mWebView.canGoBack()){
            mWebView.goBack();
        }else{
            super.onBackPressed();
        }
    }

}
