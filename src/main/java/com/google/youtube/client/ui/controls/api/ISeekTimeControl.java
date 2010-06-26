/**
 * 
 */
package com.google.youtube.client.ui.controls.api;

import com.google.youtube.client.event.chromeless.handler.HasChangeMediaSeekTimeHandler;

public interface ISeekTimeControl extends HasChangeMediaSeekTimeHandler {

	void setSeekTime(int time);
	
	void setDuration(int duration);
}