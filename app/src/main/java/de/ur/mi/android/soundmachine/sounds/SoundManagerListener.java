package de.ur.mi.android.soundmachine.sounds;

public interface SoundManagerListener {

    void onSoundReady(Sound sound);

    void onSoundStateChanged(Sound sound);

}
