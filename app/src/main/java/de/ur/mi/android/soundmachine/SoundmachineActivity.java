package de.ur.mi.android.soundmachine;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SoundmachineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_soundmachine);
    }
}
