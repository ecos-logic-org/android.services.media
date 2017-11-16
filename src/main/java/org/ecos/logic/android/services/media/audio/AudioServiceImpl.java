package org.ecos.logic.android.services.media.audio;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import org.ecos.logic.android.core.services.resolvers.ApplicationContextResolver;

import java.io.IOException;

public class AudioServiceImpl implements AudioService {
    private Context mApplicationContext;

    public AudioServiceImpl(ApplicationContextResolver applicationContextResolver) {

        mApplicationContext = applicationContextResolver.getApplicationContext();
    }

    @Override
    public void playFrom(int resourceIdentity) {
        MediaPlayer.create(mApplicationContext,resourceIdentity).start();
    }

    private Uri resourceToUri(Context context, int resourceIdentity) {
        Resources resources = context.getResources();
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
            resources.getResourcePackageName(resourceIdentity) + '/' +
            resources.getResourceTypeName(resourceIdentity) + '/' +
            resources.getResourceEntryName(resourceIdentity));
    }
}