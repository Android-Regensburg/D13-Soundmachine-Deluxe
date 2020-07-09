package de.ur.mi.android.soundmachine.sounds;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class Sound implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {

    private static int nextID = 0;
    private final String soundName;
    private boolean isReady;
    private boolean isPlaying;
    private SoundStatusListener listener;
    private MediaPlayer player;
    private int id;

    public Sound(AssetFileDescriptor soundFile, String soundName) {
        this.soundName = soundName;
        this.isReady = false;
        this.isPlaying = false;
        // Save current value of nextID, then increment for next time
        this.id = nextID++;
        prepareAudio(soundFile);
    }

    public void setSoundStatusListener(SoundStatusListener listener) {
        this.listener = listener;
    }

    public String getSoundName() {
        return soundName;
    }

    public int getId() {
        return id;
    }

    private void prepareAudio(AssetFileDescriptor soundFile) {
        try {
            player = new MediaPlayer();
            player.setOnPreparedListener(this);
            player.setOnCompletionListener(this);
            player.setDataSource(soundFile.getFileDescriptor(), soundFile.getStartOffset(), soundFile.getDeclaredLength());
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toggle() {
        if (!isReady) {
            return;
        }
        if (!isPlaying) {
            play();
        } else {
            stop();
        }

    }

    private void play() {
        player.start();
        isPlaying = true;
        if (listener != null) {
            listener.onSoundStateChanged(this);
        }
    }

    private void stop() {
        player.pause();
        player.seekTo(0);
        isPlaying = false;
        if (listener != null) {
            listener.onSoundStateChanged(this);
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        isPlaying = false;
        if (listener != null) {
            listener.onSoundStateChanged(this);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        isReady = true;
        if (listener != null) {
            listener.onSoundStateChanged(this);
        }
    }
}
