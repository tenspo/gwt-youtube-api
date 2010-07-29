package com.google.youtube.client.showcase;

import com.google.inject.Inject;
import com.philbeaudoin.gwtp.mvp.client.EventBus;
import com.philbeaudoin.gwtp.mvp.client.proxy.PlaceManagerImpl;
import com.philbeaudoin.gwtp.mvp.client.proxy.Proxy;
import com.philbeaudoin.gwtp.mvp.client.proxy.TokenFormatter;

public class ChromelessShowcasePlaceManager extends PlaceManagerImpl {

	private final Proxy<?> defaultProxy;

	@Inject
	public ChromelessShowcasePlaceManager(EventBus eventBus, TokenFormatter tokenFormatter, ChromelessShowcasePresenter.ChromelessShowcaseProxy defaultProxy) {
		super(eventBus, tokenFormatter);
		this.defaultProxy = defaultProxy;
	}

	@Override
	public void revealDefaultPlace() {
		defaultProxy.reveal();
	}

}
