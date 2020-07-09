package de.ur.mi.android.soundmachine.sounds;

public class SoundProxy {
    public final int soundID;
    public final String soundTitle;
    public final SoundState state;

    public SoundProxy(int soundID, String soundTitle, SoundState state) {
        this.soundID = soundID;
        this.soundTitle = soundTitle;
        this.state = state;
    }
}
