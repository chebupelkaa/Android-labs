package lab.lab1.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import lab.lab1.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        TextView logo = findViewById(R.id.splash);
        logo.setAlpha(1F);
        ObjectAnimator animator = ObjectAnimator.ofFloat(logo, "alpha", 0.5f);
        animator.setDuration(2000);
        animator.setRepeatCount(android.animation.ValueAnimator.INFINITE);
        animator.setRepeatMode(android.animation.ValueAnimator.REVERSE);
        animator.start();
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                }
                , 2000);
    }
}