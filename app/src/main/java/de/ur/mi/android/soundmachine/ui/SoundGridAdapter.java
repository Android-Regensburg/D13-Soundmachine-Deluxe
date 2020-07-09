package de.ur.mi.android.soundmachine.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.ur.mi.android.soundmachine.R;
import de.ur.mi.android.soundmachine.sounds.Sound;
import de.ur.mi.android.soundmachine.sounds.SoundStatusListener;

public class SoundGridAdapter extends RecyclerView.Adapter<SoundGridViewHolder> implements SoundButtonClickedListener, SoundStatusListener {

    private ArrayList<Sound> sounds;

    public SoundGridAdapter() {
        sounds = new ArrayList<>();
    }

    public void setSounds(ArrayList<Sound> sounds) {
        this.sounds = sounds;
        this.notifyDataSetChanged();
    }

    private Sound getSoundByID(int id) {
        for (Sound sound : sounds) {
            if (sound.getId() == id) {
                return sound;
            }
        }
        return null;
    }

    @NonNull
    @Override
    public SoundGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_soundbutton, parent, false);
        SoundGridViewHolder vh = new SoundGridViewHolder(v, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SoundGridViewHolder holder, int position) {
        Sound sound = sounds.get(position);
        sound.setSoundStatusListener(this);
        holder.text.setText(sound.getSoundName());
        holder.currentID = sound.getId();
        holder.setState(sound.isPlaying());
    }

    @Override
    public int getItemCount() {
        Log.d("Soundmachine", "Asking Adapter for item count: " + sounds.size());
        return sounds.size();
    }

    @Override
    public void onSoundButtonClicked(int soundID) {
        Sound clickedSound = getSoundByID(soundID);
        if (clickedSound != null) {
            clickedSound.toggle();
        }
    }

    @Override
    public void onSoundStateChanged(Sound sound) {
        this.notifyDataSetChanged();
    }

}
