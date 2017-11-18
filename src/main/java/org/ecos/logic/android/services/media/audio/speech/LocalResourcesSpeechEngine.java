package org.ecos.logic.android.services.media.audio.speech;

import android.support.annotation.IdRes;

import org.ecos.logic.android.core.exceptions.ResourceNotFoundException;
import org.ecos.logic.android.core.services.packages.InAppPackages;
import org.ecos.logic.android.core.services.packages.PackageLocation;
import org.ecos.logic.android.core.services.resolvers.ApplicationContextResolver;
import org.ecos.logic.android.core.services.resolvers.ResIdResolver;
import org.ecos.logic.android.core.services.resources.Raw;
import org.ecos.logic.android.core.services.resources.ResourceType;
import org.ecos.logic.android.services.media.audio.AudioService;
import org.ecos.logic.services.interfaces.exceptions.NotInitialized;
import org.ecos.logic.services.interfaces.speech.SpeechFinishedAction;
import org.ecos.logic.services.interfaces.speech.SpeechEngine;

public class LocalResourcesSpeechEngine implements SpeechEngine {
    private ResIdResolver mIdResolver;
    private AudioService mAudioService;

    private PackageLocation mInAppPackage;
    private ResourceType mRawResourceType;

    public LocalResourcesSpeechEngine(ResIdResolver idResolver, AudioService audioService, ApplicationContextResolver applicationContextResolver) {
        mIdResolver = idResolver;
        mAudioService = audioService;

        mInAppPackage = new InAppPackages(applicationContextResolver);
        mRawResourceType = new Raw();
    }

    @Override
    public void speak(String text) {
        @IdRes
        int resourceIdentity;
        try {
            resourceIdentity = mIdResolver.
                setLocation(mInAppPackage, mRawResourceType).
                tryGetFrom(text);
            mAudioService.playFrom(resourceIdentity);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void speak(String text, SpeechFinishedAction speechFinishedAction) throws NotInitialized {
        speak(text);

        if(speechFinishedAction != null)
            speechFinishedAction.fireFinished();
    }
}