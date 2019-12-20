package com.example;

import com.component.MainComponent;
import com.database.DbConnectionProvider;
import com.model.ShipmentBean;
import com.util.BlockchainManager;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
//@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

	private static final long serialVersionUID = -9016642298782407535L;

	public MainView(/* @Autowired MessageBean bean */) {

		Grid<ShipmentBean> grid = new Grid<>();
		grid.addColumn(ShipmentBean::getShipmentRefNo).setHeader("Shipment Ref No.").setId("shipementRefNo");
		grid.addColumn(ShipmentBean::getHouseNo).setHeader("House No.").setId("houseNo");
		grid.setItems(BlockchainManager.getShipemntBean());

		VerticalLayout layout = new VerticalLayout();
		grid.addItemClickListener(event -> {
			layout.removeAll();
			System.out.println(event.getItem());
			MainComponent component = new MainComponent(event.getItem());
			layout.add(component);
		});

		System.out.println(DbConnectionProvider.getConnection());

		boolean flag = true;
		VerticalLayout buttonLayout = new VerticalLayout();
		buttonLayout.removeAll();
		if (flag) {
			Button ok = new Button("Approve");
			Button cancel = new Button("Reject");
			buttonLayout.add(ok, cancel);

			ok.addClickListener(event -> {
				ok.setEnabled(false);
				cancel.setEnabled(false);
				doCountChnages(true, "privateKey");
			});

			cancel.addClickListener(event -> {
				ok.setEnabled(false);
				cancel.setEnabled(false);
				doCountChnages(false, "privateKey");
			});
		}
		add(grid, layout, buttonLayout);
	}

	private void doCountChnages(boolean flag, String privateKey) {
		ShipmentBean bean = BlockchainManager.getlastBlockChainForGivenKey(privateKey);
		if (flag) {
			bean.setApprovedCount(bean.getApprovedCount() + 1);
			if(bean.getApprovedCount()>=2) {
				//trigger a web request to KF and tell them to approved the shipment
				
				
			}
		} else {
            bean.setRejectedCount(bean.getRejectedCount()+1);
            if(bean.getRejectedCount()>=2) {
                //trigger a web request to KF and tell them to reject the shipment
            }
		}
	}
}
