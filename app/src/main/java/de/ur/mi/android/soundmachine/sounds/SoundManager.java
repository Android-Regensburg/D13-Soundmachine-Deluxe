package de.ur.mi.android.soundmachine.sounds;

import android.content.Context;
import android.content.res.AssetFileDescriptor;

import java.util.ArrayList;

public class SoundManager implements SoundStatusListener {

    private Context context;
    private SoundManagerListener listener;
    private ArrayList<Sound> sounds;

    public SoundManager(Context context, SoundManagerListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void loadSounds() {
        sounds = new ArrayList<>();
        for (SoundAsset asset : SoundAsset.values()) {
            AssetFileDescriptor soundFile = context.getResources().openRawResourceFd(asset.id);
            Sound sound = new Sound(soundFile, asset.title, this);
            sounds.add(sound);
        }
    }

    public void toggleSound(int soundID) {
        Sound sound = getSoundByID(soundID);
        if (sound.getSoundState() == SoundState.PLAYING) {
            sound.stop();
        } else {
            sound.play();
        }
    }

    private Sound getSoundByID(int soundID) {
        for (Sound sound : sounds) {
            if (sound.getSoundID() == soundID) {
                return sound;
            }
        }
        return null;
    }

    @Override
    public void onSoundPrepared(Sound sound) {
        listener.onSoundReady(sound);
    }

    @Override
    public void onSoundStateChanged(Sound sound) {
        listener.onSoundStateChanged(sound);
    }
}
