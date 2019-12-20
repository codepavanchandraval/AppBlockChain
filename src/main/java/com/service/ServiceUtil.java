package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.database.DbConnectionProvider;
import com.model.ShipmentBean;
import com.util.IdGenerator;

public class ServiceUtil {
	
	public static ShipmentBean getShipmentBean(String privateKey) {
		ShipmentBean bean = null;
		try {
			Connection connection = DbConnectionProvider.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select SHIPPER_REF_NO,CONSIGNEE_REF_NO,ORIGINLOCATION,DESTINATIONLOCATION  from fs_fr_housedochdr where housedocid = '"+privateKey+"'");
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

	
	public static boolean updateHashCodeInKF(String housedocId) {
		String privateKey = IdGenerator.getSHA256Hash(housedocId);
		try {
			Connection connection = DbConnectionProvider.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("update fs_fr_housedochdr set PRIVATEKEY= ? where housedocid = ?");
			statement.setString(1, privateKey);
			statement.setString(2, housedocId);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public static boolean updateStatusInKF(String housedocId, String status) {
		try {
			Connection connection = DbConnectionProvider.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("update fs_fr_housedochdr set STATUSBC= ? where housedocid = ?");
			statement.setString(1, status);
			statement.setString(2, housedocId);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
}
