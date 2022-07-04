package de.ur.mi.android.soundmachine.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.ur.mi.android.soundmachine.R;
import de.ur.mi.android.soundmachine.sounds.SoundProxy;

public class SoundGridAdapter extends RecyclerView.Adapter<SoundGridViewHolder> {

    private final ArrayList<SoundProxy> soundProxies;
    private final SoundButtonClickedListener soundButtonClickedListener;

    public SoundGridAdapter(SoundButtonClickedListener soundButtonClickedListener) {
        this.soundButtonClickedListener = soundButtonClickedListener;
        soundProxies = new ArrayList<>();
    }

    public void addSoundProxy(SoundProxy proxy) {
        soundProxies.add(proxy);
        this.notifyItemChanged(soundProxies.indexOf(proxy));
    }

    public void updateSoundProxy(SoundProxy proxy) {
        for (int i = 0; i < soundProxies.size(); i++) {
            if (soundProxies.get(i).soundID == proxy.soundID) {
                soundProxies.set(i, proxy);
                break;
            }
        }
        this.notifyItemChanged(soundProxies.indexOf(proxy));
    }

    @NonNull
    @Override
    public SoundGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_soundbutton, parent, false);
        return new SoundGridViewHolder(v, soundButtonClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SoundGridViewHolder holder, int position) {
        holder.bindSound(soundProxies.get(position));
    }

    @Override
    public int getItemCount() {
        return soundProxies.size();
    }

}
