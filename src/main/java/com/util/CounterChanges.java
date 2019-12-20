package com.util;

import com.model.ShipmentBean;
import com.service.ServiceUtil;

public class CounterChanges {

	public static void doCountChnages(boolean flag, String privateKey,String houseId) {
		ShipmentBean bean = BlockchainManager.getlastBlockChainForGivenKey(privateKey, 1);
		if (flag) {
			bean.setApprovedCount(bean.getApprovedCount() + 1);
			if (bean.getApprovedCount() >= 2) {
				ServiceUtil.updateStatusInKF(houseId, "APPROVED");
			}
		} else {
			bean.setRejectedCount(bean.getRejectedCount() + 1);
			if (bean.getRejectedCount() >= 2) {
				ServiceUtil.updateStatusInKF(houseId, "REJECTED");
			}
		}
	}

}
