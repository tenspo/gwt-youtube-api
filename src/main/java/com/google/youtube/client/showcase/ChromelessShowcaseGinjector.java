package com.google.youtube.client.showcase;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.philbeaudoin.gwtp.mvp.client.EventBus;
import com.philbeaudoin.gwtp.mvp.client.proxy.PlaceManager;
import com.philbeaudoin.gwtp.mvp.client.proxy.ProxyFailureHandler;
import com.sun.xml.internal.ws.api.server.AsyncProvider;

@GinModules({ChromelessShowcaseModule.class})
public interface ChromelessShowcaseGinjector extends Ginjector {

	PlaceManager getPlaceManager();

	EventBus getEventBus();

	ProxyFailureHandler getProxyFailureHandler();

	AsyncProvider<ChromelessShowcasePresenter> getShowcasePresenter();

}
