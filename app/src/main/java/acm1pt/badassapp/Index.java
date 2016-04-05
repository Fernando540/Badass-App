package acm1pt.badassapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

public class index extends AppCompatActivity implements Animation.AnimationListener {

    static int IMAGE_NUMBER = 1;

    RelativeLayout SplashScreen_rLayout;
    Animation animZoomIn, fadeIn, fadeOut;
    Button btnLog, btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        SplashScreen_rLayout = (RelativeLayout) findViewById(R.id.rLayout_Splash);
        btnLog = (Button) findViewById(R.id.btnLogin);

        animZoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);


        animZoomIn.setAnimationListener(this);
        fadeOut.setAnimationListener(this);
        fadeIn.setAnimationListener(this);


        SplashScreen_rLayout.startAnimation(animZoomIn);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(index.this,
                        login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if (animation == animZoomIn) {
            SplashScreen_rLayout.startAnimation(fadeOut);
        } else if (animation == fadeOut) {
            if (IMAGE_NUMBER == 1) {
                IMAGE_NUMBER = 2;
                SplashScreen_rLayout.setBackgroundResource(R.drawable.background2);
                SplashScreen_rLayout.startAnimation(fadeIn);
            } else if (IMAGE_NUMBER == 2) {
                IMAGE_NUMBER = 3;
                SplashScreen_rLayout.setBackgroundResource(R.drawable.background3);
                SplashScreen_rLayout.startAnimation(fadeIn);
            } else if (IMAGE_NUMBER == 3) {
                IMAGE_NUMBER = 4;
                SplashScreen_rLayout.setBackgroundResource(R.drawable.background4);
                SplashScreen_rLayout.startAnimation(fadeIn);
            }else if (IMAGE_NUMBER == 4) {
                IMAGE_NUMBER = 5;
                SplashScreen_rLayout.setBackgroundResource(R.drawable.background5);
                SplashScreen_rLayout.startAnimation(fadeIn);
            }else {
                IMAGE_NUMBER = 1;
                SplashScreen_rLayout.setBackgroundResource(R.drawable.background1);
                SplashScreen_rLayout.startAnimation(fadeIn);
            }

        } else if (animation == fadeIn) {
            SplashScreen_rLayout.startAnimation(animZoomIn);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }
}
