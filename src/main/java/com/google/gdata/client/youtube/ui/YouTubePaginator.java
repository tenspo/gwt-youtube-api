package com.google.gdata.client.youtube.ui;

import com.google.gdata.client.Query;
import com.google.gdata.client.QueryPage;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class YouTubePaginator extends Composite {

	private QueryPage queryPage = null;

	public static final int MAX_OFFSET = 3;
	
	private static final YouTubeMessages messages = GWT.create(YouTubeMessages.class);
	
	private FlowPanel panel;
	
	private static final String BUTTON_STYLE = "youtube-paginator-button";
	private static final String LABEL_STYLE = "youtube-paginator-label";
		
	public static interface PagingHandler<T> extends EventHandler {
		void onPage(PagingEvent<T> event);
	}

	public static class PagingEvent<T> extends GwtEvent<PagingHandler<T>> {

		private static Type<PagingHandler<?>> TYPE;

		public static <T> void fire(YouTubePaginator source, int pageIndex) {
			if (TYPE != null) {
				PagingEvent<T> event = new PagingEvent<T>(pageIndex);
				source.fireEvent(event);
			}
		}

		public static Type<PagingHandler<?>> getType() {
			return TYPE != null ? TYPE : (TYPE = new Type<PagingHandler<?>>());
		}

		private int pageIndex;

		protected PagingEvent(int pageIndex) {
			this.pageIndex = pageIndex;
		}

		@SuppressWarnings("unchecked")
		@Override
		public final Type<PagingHandler<T>> getAssociatedType() {
			return (Type) TYPE;
		}

		public int getPageIndex() {
			return pageIndex;
		}

		@Override
		protected void dispatch(PagingHandler<T> handler) {
			handler.onPage(this);
		}
	}

	protected Label createLabel(String label) {
		Label textLabel = new Label();
		textLabel.setText(label);
		textLabel.setStyleName(LABEL_STYLE);
		return textLabel;
	}
	
	protected Button createButton(String label, boolean disabled) {
		Button button = new Button(label);
		if (disabled) {
			button.setEnabled(false);
			button.setStyleName(BUTTON_STYLE + "-disabled");
		} else {
			button.setStyleName(BUTTON_STYLE);
		}
		return button;
	}
	
	public YouTubePaginator() {
		panel = new FlowPanel();
		initWidget(panel);
	}

	private Widget addEventHandler(HasClickHandlers w, final int pageIndex) {
		w.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				YouTubePaginator.this.queryPage.setStartIndex((pageIndex - 1) * YouTubePaginator.this.queryPage.getItemsPerPage() + 1);
				PagingEvent.fire(YouTubePaginator.this, pageIndex);
			}
		});
		return (Widget)w;
	}
	
	public void setQueryPage(QueryPage queryPage) {
		this.queryPage = queryPage;
	}
	
	public void showPagingButtons() {
		
		int pageIndex = queryPage.getStartIndex() / queryPage.getItemsPerPage() + 1;
		int maxPageIndex = Query.UNDEFINED;
		
		if (queryPage.getTotalResults() != Query.UNDEFINED) {
			maxPageIndex = queryPage.getTotalResults() / queryPage.getItemsPerPage();
		}
		
		if (pageIndex > 1) {
			panel.add(addEventHandler(createLabel(messages.previous()), pageIndex-1));
		}
		
		int min = Math.max(1, pageIndex-MAX_OFFSET);
		
		for (int i = min; (i < min + 2 * MAX_OFFSET + 1) && (maxPageIndex == Query.UNDEFINED || i < maxPageIndex); i++) {
			panel.add(addEventHandler(createButton(String.valueOf(i), i == pageIndex), i));
		}

		if (pageIndex < maxPageIndex && maxPageIndex != Query.UNDEFINED) {
			panel.add(addEventHandler(createLabel(messages.next()), pageIndex + 1));
		}
	}

	public HandlerRegistration addPagingHandler(PagingHandler<YouTubePaginator> handler) {
		return addHandler(handler, PagingEvent.getType());
	}
}
