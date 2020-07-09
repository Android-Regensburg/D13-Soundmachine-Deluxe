package de.ur.mi.android.soundmachine.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.ur.mi.android.soundmachine.R;
import de.ur.mi.android.soundmachine.sounds.SoundProxy;
import de.ur.mi.android.soundmachine.sounds.SoundState;

public class SoundGridAdapter extends RecyclerView.Adapter<SoundGridViewHolder> {

    private ArrayList<SoundProxy> soundProxies;
    private SoundButtonClickedListener soundButtonClickedListener;

    public SoundGridAdapter(SoundButtonClickedListener soundButtonClickedListener) {
        this.soundButtonClickedListener = soundButtonClickedListener;
        soundProxies = new ArrayList<>();
    }

    public void addSoundProxy(SoundProxy proxy) {
        soundProxies.add(proxy);
        this.notifyDataSetChanged();
    }

    public void updateSoundProxy(SoundProxy proxy) {
        for (int i = 0; i < soundProxies.size(); i++) {
            if (soundProxies.get(i).soundID == proxy.soundID) {
                soundProxies.set(i, proxy);
                break;
            }
        }
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SoundGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_soundbutton, parent, false);
        SoundGridViewHolder vh = new SoundGridViewHolder(v, soundButtonClickedListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SoundGridViewHolder holder, int position) {
        SoundProxy proxy = soundProxies.get(position);
        holder.currentID = proxy.soundID;
        holder.text.setText(proxy.soundTitle);
        if (proxy.state == SoundState.PLAYING) {
            holder.setPlayingState();
        } else {
            holder.setReadyState();
        }
    }

    @Override
    public int getItemCount() {
        return soundProxies.size();
    }

}
