package de.ur.mi.android.soundmachine;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.ur.mi.android.soundmachine.config.AppConfig;
import de.ur.mi.android.soundmachine.sounds.Sound;
import de.ur.mi.android.soundmachine.ui.SoundGridAdapter;

public class SoundmachineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_soundmachine);
        ArrayList<Sound> sounds = createSoundList();
        SoundGridAdapter gridAdapter = new SoundGridAdapter();
        RecyclerView soundGrid = findViewById(R.id.sound_grid);
        soundGrid.setAdapter(gridAdapter);
        gridAdapter.setSounds(sounds);
    }

    private ArrayList<Sound> createSoundList() {
        ArrayList<Sound> sounds = new ArrayList<>();
        int[] soundIDs = AppConfig.SOUND_IDS;
        String[] soundNames = getResources().getStringArray(R.array.sound_names);
        for (int i = 0; i < soundIDs.length; i++) {
            AssetFileDescriptor soundFile = getResources().openRawResourceFd(soundIDs[i]);
            String soundName = soundNames[i];
            Sound sound = new Sound(soundFile, soundName);
            sounds.add(sound);
        }
        return sounds;
    }


}
