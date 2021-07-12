package de.ur.mi.android.soundmachine.sounds;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class Sound implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {

    private static int nextID = 0;
    private final int soundID;
    private final String soundTitle;
    private final SoundStatusListener listener;
    private final MediaPlayer player;
    private SoundState state;

    public Sound(AssetFileDescriptor soundFile, String soundName, SoundStatusListener listener) {
        this.soundTitle = soundName;
        this.listener = listener;
        // Save current value of nextID, then increment for next time
        this.soundID = nextID++;
        this.state = SoundState.LOADING;
        this.player = createMediaPlayerForFile(soundFile);
    }

    private MediaPlayer createMediaPlayerForFile(AssetFileDescriptor soundFile) {
        MediaPlayer player = new MediaPlayer();
        try {
            player.setOnPreparedListener(this);
            player.setOnCompletionListener(this);
            player.setDataSource(soundFile.getFileDescriptor(), soundFile.getStartOffset(), soundFile.getDeclaredLength());
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return player;
    }

    public int getSoundID() {
        return soundID;
    }

    public SoundState getSoundState() {
        return state;
    }

    public SoundProxy getProxy() {
        return new SoundProxy(soundID, soundTitle, state);
    }

    public void play() {
        if (state != SoundState.READY) {
            return;
        }
        player.start();
        state = SoundState.PLAYING;
        if (listener != null) {
            listener.onSoundStateChanged(this);
        }
    }

    public void stop() {
        if (state != SoundState.PLAYING) {
            return;
        }
        player.pause();
        player.seekTo(0);
        state = SoundState.READY;
        if (listener != null) {
            listener.onSoundStateChanged(this);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        state = SoundState.READY;
        if (listener != null) {
            listener.onSoundStateChanged(this);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        state = SoundState.READY;
        if (listener != null) {
            listener.onSoundPrepared(this);
        }
    }
}
