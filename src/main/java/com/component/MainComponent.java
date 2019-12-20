package com.component;


import java.util.List;

import com.model.ShipmentBean;
import com.util.BlockchainManager;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class MainComponent extends HorizontalLayout {

	private static final long serialVersionUID = 1771191052840024156L;

	public MainComponent(List<ShipmentBean> listData) {
		setMargin(false);
		if (listData == null) {
			this.setVisible(false);
			return;
		}
		for (ShipmentBean bean : listData) {
			MultiObjectComponent component = new MultiObjectComponent(bean);
			this.add(component);
		}
	}
	
	public MainComponent(ShipmentBean mainBean) {
		setMargin(false);
		if (mainBean == null) {
			this.setVisible(false);
			return;
		}
		for (ShipmentBean bean : BlockchainManager.getBlockChains(mainBean)) {
			MultiObjectComponent component = new MultiObjectComponent(bean);
			this.add(component);
		}
	}
}
