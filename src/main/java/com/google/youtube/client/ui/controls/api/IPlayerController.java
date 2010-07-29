package com.google.youtube.client.ui.controls.api;

import com.google.gdata.data.youtube.VideoEntry;
import com.google.youtube.client.event.chromeless.handler.HasChangeMediaSeekTimeHandler;
import com.google.youtube.client.event.chromeless.handler.HasPauseMediaHandler;
import com.google.youtube.client.event.chromeless.handler.HasPlayMediaHandler;
import com.google.youtube.client.event.chromeless.handler.HasStopMediaHandler;

public interface IPlayerController extends HasPauseMediaHandler, HasPlayMediaHandler, HasStopMediaHandler,
		HasChangeMediaSeekTimeHandler {

	public void initializeFromEntry(VideoEntry entry);
}