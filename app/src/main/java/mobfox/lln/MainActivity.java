package mobfox.lln;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobfox.sdk.banner.Banner;
import com.mobfox.sdk.customevents.CustomEventNative;
import com.mobfox.sdk.nativeads.Native;
import com.mobfox.sdk.nativeads.NativeAd;
import com.mobfox.sdk.nativeads.NativeListener;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private Native aNative;
    private Activity self;

    private NativeListener listener;

    //creating variables for our layout
    TextView headline;
    ImageView nativeIcon, nativeMainImg;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        self = this;

        headline = findViewById(R.id.headline);
        nativeIcon = findViewById(R.id.nativeIcon);
        nativeMainImg = findViewById(R.id.nativeMainImg);
        layout = findViewById(R.id.nativeLayout);

        aNative = new Native(this);

        listener = new NativeListener() {
            @Override
            public void onNativeReady(Native aNative, CustomEventNative event, NativeAd ad) {

                Toast.makeText(self, "on native ready", Toast.LENGTH_SHORT).show();

                event.registerViewForInteraction(layout);
                ad.fireTrackers(self);

                headline.setText(ad.getTexts().get(0).getText());

                ad.loadImages(self, new NativeAd.ImagesLoadedListener() {
                    @Override
                    public void onImagesLoaded(NativeAd ad) {
                        Toast.makeText(self, "on images ready", Toast.LENGTH_SHORT).show();
                        nativeIcon.setImageBitmap(ad.getImages().get(0).getImg());
                    }
                });

            }

            @Override
            public void onNativeError(Exception e) {

                Log.d(TAG, "onNativeError: " + e);
            }

            @Override
            public void onNativeClick(NativeAd ad) {
                Toast.makeText(self, "on native click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNativeOpened(Native aNative) {

            }

        };

        aNative.setListener(listener);

        aNative.load("bf816f9e314b24aac0c33a0b76855c22");


    }


}
