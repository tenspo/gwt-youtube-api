package com.google.youtube.client.showcase;

import com.google.inject.Inject;
import com.google.youtube.client.showcase.ChromelessShowcasePresenter.ChromelessShowcaseDisplay;
import com.google.youtube.client.showcase.ChromelessShowcasePresenter.ChromelessShowcaseProxy;
import com.philbeaudoin.gwtp.mvp.client.EventBus;
import com.philbeaudoin.gwtp.mvp.client.PresenterImpl;
import com.philbeaudoin.gwtp.mvp.client.View;
import com.philbeaudoin.gwtp.mvp.client.proxy.ProxyPlace;
import com.philbeaudoin.gwtp.mvp.client.proxy.RevealRootContentEvent;

public class ChromelessShowcasePresenter extends PresenterImpl<ChromelessShowcaseDisplay, ChromelessShowcaseProxy> {

	@Inject
	public ChromelessShowcasePresenter(EventBus eventBus, ChromelessShowcaseDisplay view, ChromelessShowcaseProxy proxy) {
		super(eventBus, view, proxy);
	}

	public interface ChromelessShowcaseDisplay extends View {}

	public interface ChromelessShowcaseProxy extends ProxyPlace<ChromelessShowcasePresenter> {}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(eventBus, this);
	}
}