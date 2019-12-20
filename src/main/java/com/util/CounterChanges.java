package com.util;

import com.model.ShipmentBean;
import com.service.ServiceUtil;

public class CounterChanges {

	public static void doCountChnages(boolean flag, String privateKey,String houseId) {
		ShipmentBean bean = BlockchainManager.getlastBlockChainForGivenKey(privateKey, 1);
		if (flag) {
			bean.setApprovedCount(bean.getApprovedCount() + 1);
			if (bean.getApprovedCount() >= 3) {
				ServiceUtil.updateStatusInKF(houseId, "APPROVED");
			}
		} else {
			ShipmentBean b = BlockchainManager.getlastBlockChainForGivenKey(privateKey, 2);
			bean.setRejectedCount(bean.getRejectedCount() + 1);
			if (bean.getRejectedCount() >= 3) {
				ServiceUtil.updateStatusInKF(houseId, "REJECTED");
				ServiceUtil.updateStatusInKF1(houseId, b);
			}
		}
	}

}
