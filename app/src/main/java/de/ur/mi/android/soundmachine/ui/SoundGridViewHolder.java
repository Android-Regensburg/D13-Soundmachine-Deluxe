package de.ur.mi.android.soundmachine.ui;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import de.ur.mi.android.soundmachine.R;
import de.ur.mi.android.soundmachine.sounds.SoundProxy;
import de.ur.mi.android.soundmachine.sounds.SoundState;

public class SoundGridViewHolder extends RecyclerView.ViewHolder {

    private final ImageButton button;
    private final TextView text;
    private int currentID;

    public SoundGridViewHolder(View v, final SoundButtonClickedListener listener) {
        super(v);
        button = v.findViewById(R.id.sound_button);
        text = v.findViewById(R.id.sound_title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSoundButtonClicked(currentID);
            }
        });
    }

    public void bindSound(SoundProxy sound) {
        currentID = sound.soundID;
        text.setText(sound.soundTitle);
        if (sound.state == SoundState.PLAYING) {
            button.setBackgroundResource(R.drawable.button_background_playing);
        } else {
            button.setBackgroundResource(R.drawable.button_background);
        }
    }
}
