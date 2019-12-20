package com.example;

import com.component.MainComponent;
import com.model.ShipmentBean;
import com.util.BlockchainManager;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.component.splitlayout.SplitLayoutVariant;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

	private static final long serialVersionUID = -9016642298782407535L;

	public MainView() {
		SplitLayout sVlayout = new SplitLayout();
		sVlayout.setOrientation(Orientation.VERTICAL);
		sVlayout.setSplitterPosition(50);

		Grid<ShipmentBean> grid = new Grid<>();
		grid.addColumn(ShipmentBean::getHouseNo).setHeader("House ID").setId("House ID");
		grid.addColumn(new LocalDateTimeRenderer<>(ShipmentBean::getDate, "dd/MM HH:mm:ss")).setHeader("Creation Date").setId("Creation Date");
		grid.setItems(BlockchainManager.getShipemntBean());
		grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_WRAP_CELL_CONTENT);
		grid.setColumnReorderingAllowed(true);

		VerticalLayout vLayout = new VerticalLayout();
		grid.addItemClickListener(event -> {
			vLayout.removeAll();
			MainComponent component = new MainComponent(event.getItem());
			vLayout.add(component);
			vLayout.setSizeFull();
		});
		
		sVlayout.addToPrimary(grid);
		sVlayout.addToSecondary(vLayout);
		sVlayout.addThemeVariants(SplitLayoutVariant.LUMO_SMALL);
		sVlayout.setSizeFull();
		
		add(sVlayout);
		setSizeFull();
		
	}

}
