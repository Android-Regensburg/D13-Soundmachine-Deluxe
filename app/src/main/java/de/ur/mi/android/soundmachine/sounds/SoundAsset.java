package de.ur.mi.android.soundmachine.sounds;

import de.ur.mi.android.soundmachine.R;

public enum SoundAsset {
    X_FILES(R.raw.aktex, "X-Files"),
    ALERT(R.raw.alert, "Alert"),
    BADUMTS(R.raw.badumts, "Badumts!"),
    CENA(R.raw.cena, "Cena"),
    CRICKETS(R.raw.crickets, "Crickets"),
    DOLPHIN(R.raw.dolphin, "Dolphin"),
    DRUMS(R.raw.drums, "Drums"),
    KOBE(R.raw.kobe, "Kobe"),
    SCREAM(R.raw.scream, "Scream"),
    HELL_NO(R.raw.hellno, "Hell no!"),
    SURPRISE(R.raw.surprise, "Surprise!"),
    TO_BE_CONTINUED(R.raw.tobecontinued, "To be ...");

    public final int id;
    public final String title;

    SoundAsset(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
