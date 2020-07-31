package com.hermes.sqr;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private static final int PLAY_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // check permission
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                startAnimation();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(getApplicationContext(), R.string.permission_denied_message, Toast.LENGTH_SHORT).show();
                exit();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(R.string.permission_reason)
                .setDeniedMessage(R.string.permission_denied_message)
                .setPermissions(Manifest.permission.CAMERA)
                .check();
    }

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {

        }

        @Override
        public void onAnimationEnd(Animator animator) {
            goToNextActivity();
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    };

    private void startAnimation() {
        YoYo.with(Techniques.FadeIn)
                .duration(PLAY_TIME)
                .withListener(animatorListener)
                .playOn(findViewById(R.id.intro_logo));
    }

    private void goToNextActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void exit() {
        finish();
        System.exit(0);
    }
}