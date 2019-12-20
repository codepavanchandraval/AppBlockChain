package com.component;

import com.model.ShipmentBean;
import com.util.IdGenerator;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class MultiObjectComponent extends VerticalLayout{

	private static final long serialVersionUID = -2125685103332008921L;
	
	private TextField originBranchDepartmentTextField;
	private TextField destinationBranchDepartment;
	private TextField pol_pod;
	private TextField consinee;
	
	public MultiObjectComponent(ShipmentBean bean) {
		init(bean);
		setSizeFull();
		setMargin(true);
	}

	private void init(ShipmentBean bean) {
		originBranchDepartmentTextField = new TextField("Origin Branch Department");
		originBranchDepartmentTextField.setValue(bean.getOriginBranchDepartment());
		originBranchDepartmentTextField.setReadOnly(true);
		
		destinationBranchDepartment = new TextField("Destination Branch Department");
		destinationBranchDepartment.setValue(bean.getDestinationBranchDepartment());
		destinationBranchDepartment.setReadOnly(true);
		
		pol_pod = new TextField("POL-POD");
		pol_pod.setValue(bean.getPol_pod());
		pol_pod.setReadOnly(true);
	
		consinee =new TextField("Consinee");
		consinee.setValue(bean.getConsinee());
		consinee.setReadOnly(true);
		
		TextField hashCode =new TextField("HashCode");
		hashCode.setValue(IdGenerator.getSHA256Hash(bean.getShipmentRefNo()+bean.getDate()));
		hashCode.setReadOnly(true);
		
		this.add(originBranchDepartmentTextField,destinationBranchDepartment,pol_pod,consinee,hashCode);
	}
}
