package com.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.database.DbConnectionProvider;
import com.model.ShipmentBean;

public class ServiceUtil {
	
	public static ShipmentBean getShipmentBean(String privateKey) {
		ShipmentBean bean = null;
		try {
			Connection connection = DbConnectionProvider.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select SHIPPER_REF_NO,CONSIGNEE_REF_NO,ORIGINLOCATION,DESTINATIONLOCATION  from fs_fr_housedochdr where housedocid = '10388'");
			if (resultSet.next()) {
				bean = new ShipmentBean();
				bean.setShipmentRefNo(resultSet.getString("SHIPPER_REF_NO"));
				bean.setConsinee(resultSet.getString("CONSIGNEE_REF_NO"));
				bean.setOriginBranchDepartment(resultSet.getString("ORIGINLOCATION"));
				bean.setDestinationBranchDepartment(resultSet.getString("DESTINATIONLOCATION"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return bean;
	}

}
