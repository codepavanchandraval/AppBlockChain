package com.util;

import com.model.ShipmentBean;

public class CounterChanges {

	public static void doCountChnages(boolean flag, String privateKey) {
		ShipmentBean bean = BlockchainManager.getlastBlockChainForGivenKey(privateKey, 1);
		if (flag) {
			bean.setApprovedCount(bean.getApprovedCount() + 1);
			if (bean.getApprovedCount() >= 2) {
				// trigger a web request to KF and tell them to approved the shipment

			}
		} else {
			bean.setRejectedCount(bean.getRejectedCount() + 1);
			if (bean.getRejectedCount() >= 2) {
				// trigger a web request to KF and tell them to reject the shipment
			}
		}
	}

}
