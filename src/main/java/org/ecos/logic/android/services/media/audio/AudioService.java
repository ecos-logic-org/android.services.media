package org.ecos.logic.android.services.media.audio;

import android.support.annotation.IdRes;

public interface AudioService {
    void playFrom(@IdRes int resourceIdentity);
}
