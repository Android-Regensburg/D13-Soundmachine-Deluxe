package de.ur.mi.android.soundmachine;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import de.ur.mi.android.soundmachine.sounds.Sound;
import de.ur.mi.android.soundmachine.sounds.SoundManager;
import de.ur.mi.android.soundmachine.sounds.SoundManagerListener;
import de.ur.mi.android.soundmachine.ui.SoundButtonClickedListener;
import de.ur.mi.android.soundmachine.ui.SoundGridAdapter;

public class SoundmachineActivity extends AppCompatActivity implements SoundManagerListener, SoundButtonClickedListener {

    private SoundManager soundManager;
    SoundGridAdapter soundGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initSounds();
    }

    private void initUI() {
        setContentView(R.layout.activity_soundmachine);
        soundGrid = new SoundGridAdapter(this);
        RecyclerView soundGridView = findViewById(R.id.sound_grid);
        soundGridView.setAdapter(soundGrid);
    }

    private void initSounds() {
        soundManager = new SoundManager(this, this);
        soundManager.loadSounds();
    }

    @Override
    public void onSoundReady(Sound sound) {
        soundGrid.addSoundProxy(sound.getProxy());
    }

    @Override
    public void onSoundStateChanged(Sound sound) {
        soundGrid.updateSoundProxy(sound.getProxy());
    }

    @Override
    public void onSoundButtonClicked(int soundID) {
        soundManager.toggleSound(soundID);
    }
}
