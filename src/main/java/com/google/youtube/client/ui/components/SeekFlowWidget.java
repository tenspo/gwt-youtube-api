/**
 * 
 */
package com.google.youtube.client.ui.components;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.youtube.client.ui.components.api.ISeekWidget;

public class SeekFlowWidget extends FlowPanel implements ISeekWidget {

	private Button seekButton;
	private TextBox seekValueBox;
	private Label seekLabel;

	public Label getSeekLabel() {
		return seekLabel;
	}

	public void setSeekLabel(Label seekLabel) {
		this.seekLabel = seekLabel;
	}

	public Button getSeekButton() {
		return seekButton;
	}

	public void setSeekButton(Button seekButton) {
		this.seekButton = seekButton;
	}

	public TextBox getSeekValueBox() {
		return seekValueBox;
	}

	public void setSeekValueBox(TextBox seekValueBox) {
		this.seekValueBox = seekValueBox;
	}

	@Override
	protected void onLoad() {

		if (seekValueBox != null) {
			add(seekValueBox);
			seekValueBox.setWidth("60px");
			seekValueBox.setText("0");
		}

		if (seekLabel != null) {
			add(seekLabel);
		}

		if (seekButton != null) {
			add(seekButton);
		}
		super.onLoad();
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return seekButton.addClickHandler(handler);
	}

	@Override
	public Integer getSeekTime() {
		if (seekValueBox != null) {
			String seekValue = seekValueBox.getText();
			try {
				return Integer.parseInt(seekValue);
			} catch (NumberFormatException ex) {
				return null;
			}
		}

		return null;
	}
}