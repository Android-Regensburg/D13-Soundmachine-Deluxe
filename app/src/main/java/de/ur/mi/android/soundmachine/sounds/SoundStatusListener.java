package de.ur.mi.android.soundmachine.sounds;

public interface SoundStatusListener {

    void onSoundPrepared(Sound sound);

    void onSoundStateChanged(Sound sound);

}