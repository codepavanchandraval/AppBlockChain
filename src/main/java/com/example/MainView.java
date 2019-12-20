package com.example;

import com.component.MainComponent;
import com.model.ShipmentBean;
import com.util.BlockchainManager;
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
		
		add(grid,layout);
	}
}
