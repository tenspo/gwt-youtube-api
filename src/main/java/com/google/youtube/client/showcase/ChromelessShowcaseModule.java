package com.google.youtube.client.showcase;

import com.google.inject.Singleton;
import com.google.youtube.client.ui.controls.youtube.PositionControlPresenter;
import com.google.youtube.client.ui.controls.youtube.PositionControlView;
import com.philbeaudoin.gwtp.mvp.client.DefaultEventBus;
import com.philbeaudoin.gwtp.mvp.client.DefaultProxyFailureHandler;
import com.philbeaudoin.gwtp.mvp.client.EventBus;
import com.philbeaudoin.gwtp.mvp.client.RootPresenter;
import com.philbeaudoin.gwtp.mvp.client.gin.AbstractPresenterModule;
import com.philbeaudoin.gwtp.mvp.client.proxy.ParameterTokenFormatter;
import com.philbeaudoin.gwtp.mvp.client.proxy.PlaceManager;
import com.philbeaudoin.gwtp.mvp.client.proxy.ProxyFailureHandler;
import com.philbeaudoin.gwtp.mvp.client.proxy.TokenFormatter;

public class ChromelessShowcaseModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		bind(PlaceManager.class).to(ChromelessShowcasePlaceManager.class).in(Singleton.class);
		bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);
		bind(RootPresenter.class).asEagerSingleton();
		bind(ProxyFailureHandler.class).to(DefaultProxyFailureHandler.class).in(Singleton.class);

		// Presenters
		bindPresenter(PositionControlPresenter.class, PositionControlPresenter.PositionControlDisplay.class, PositionControlView.class,
				PositionControlPresenter.PositionControlProxy.class);
		bindPresenter(ChromelessShowcasePresenter.class, ChromelessShowcasePresenter.ChromelessShowcaseDisplay.class, ChromelessShowcaseView.class,
				ChromelessShowcasePresenter.ChromelessShowcaseProxy.class);
	}
}