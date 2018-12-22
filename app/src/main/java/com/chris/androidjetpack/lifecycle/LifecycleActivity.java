package com.chris.androidjetpack.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.chris.androidjetpack.R;
import com.chris.androidjetpack.databinding.DataBindingActivity;

/**
 * Created by jianjianhong on 18-12-16
 */
public class LifecycleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        getLifecycle().addObserver(new TestLifeCycle());
    }

    public void toMeActivity(View view) {
        startActivity(new Intent(this, DataBindingActivity.class));
    }
}
