package de.ur.mi.android.soundmachine.sounds;

import de.ur.mi.android.soundmachine.R;

public enum SoundAsset {
    BADUMTS(R.raw.badumts, "Badumts"),
    BELLS(R.raw.bells, "Bells"),
    CAT(R.raw.cat, "Cat"),
    COUNTDOWN(R.raw.countdown, "Countdown"),
    CRICKETS(R.raw.crickets, "Crickets"),
    DOLPHIN(R.raw.dolphin, "Dolphin"),
    FART(R.raw.fart, "Fart"),
    FUNKY(R.raw.funky, "Funky"),
    OUTRO(R.raw.outro, "Outro"),
    PAIN(R.raw.pain, "Pain"),
    SCIFI(R.raw.scifi, "Scifi"),
    WILHELM(R.raw.wilhelm, "Wilhelm");

    public final int id;
    public final String title;

    SoundAsset(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
