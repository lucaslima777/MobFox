# MobFox

NativeAd MobFox

## XML


``` xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:mobfox="http://schemas.android.com/apk/lib/com.mobfox.sdk"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/spinnerInvh"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <RelativeLayout
        android:id="@+id/nativeLayout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/spinnerInvh"
        tools:ignore="MissingConstraints">

        <TextView
            android:id="@+id/headline"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <ImageView
            android:id="@+id/nativeIcon"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_below="@+id/headline"
            android:adjustViewBounds="true"
            android:maxWidth="300dp"
            android:maxHeight="300dp"
            android:scaleType="fitCenter" />

        <ImageView
            android:id="@+id/nativeMainImg"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_below="@+id/nativeIcon"
            android:adjustViewBounds="true"
            android:maxHeight="300dp"
            android:scaleType="fitCenter" />
    </RelativeLayout>

</android.support.constraint.ConstraintLayout>
```

## JAVA

```java
import com.mobfox.sdk.nativeads.Native;
import com.mobfox.sdk.nativeads.NativeAd;
import com.mobfox.sdk.nativeads.NativeListener;
import com.mobfox.sdk.customevents.CustomEventNative;
  
    private Native aNative;
    private Activity self;
 
    private NativeListener listener;
     
    TextView headline;
    ImageView nativeIcon, nativeMainImg;
    RelativeLayout layout;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_layout);
 
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
                Toast.makeText(self, "on native error", Toast.LENGTH_SHORT).show();
            }
 
            @Override
            public void onNativeClick(NativeAd ad) {
                Toast.makeText(self, "on native click", Toast.LENGTH_SHORT).show();
            }
             
        };
 
        aNative.setListener(listener);
 
        aNative.load("<your-publication-hash>");
    }
```

## ANDROID MANIFEST

``` xml

<application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        
    <!--ADD-->
    <meta-data
        android:name="com.google.android.gms.ads.AD_MANAGER_APP"
        android:value="true"/>
        
    </application>
```


# Developed By

* Lucas Lima 
 * :email: e-mail: lucaslimatorre@gmail.com
 
 [![LinkedIn](https://img.shields.io/badge/LinkedIn-LucasLima-blue.svg)](https://www.linkedin.com/in/lucas-lima-torre/)
 

![GitHub issue age](https://img.shields.io/badge/build-android%20studio-brightgreen.svg)



# License

    Copyright 2018 Lucas Lima

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
