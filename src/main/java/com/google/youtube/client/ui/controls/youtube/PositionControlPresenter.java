package com.google.youtube.client.ui.controls.youtube;

import com.google.inject.Inject;
import com.google.youtube.client.ui.controls.youtube.PositionControlPresenter.PositionControlDisplay;
import com.google.youtube.client.ui.controls.youtube.PositionControlPresenter.PositionControlProxy;
import com.philbeaudoin.gwtp.mvp.client.EventBus;
import com.philbeaudoin.gwtp.mvp.client.PresenterImpl;
import com.philbeaudoin.gwtp.mvp.client.View;
import com.philbeaudoin.gwtp.mvp.client.annotations.ProxyStandard;
import com.philbeaudoin.gwtp.mvp.client.proxy.ProxyPlace;
import com.philbeaudoin.gwtp.mvp.client.proxy.RevealRootContentEvent;

public class PositionControlPresenter extends PresenterImpl<PositionControlDisplay, PositionControlProxy> {

	@Inject
	public PositionControlPresenter(EventBus eventBus, PositionControlDisplay view, PositionControlProxy proxy) {
		super(eventBus, view, proxy);
	}

	public interface PositionControlDisplay extends View {}

	@ProxyStandard
	public interface PositionControlProxy extends ProxyPlace<PositionControlPresenter> {}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(eventBus, this);
	}
}