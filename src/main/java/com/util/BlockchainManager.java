package com.util;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;

import com.datastructure.BlockChainDatastructure;
import com.datastructure.BlockChainDatastructureImpl;
import com.model.ShipmentBean;


public class BlockchainManager {
	public static LinkedHashMap<String, BlockChainDatastructure<ShipmentBean>> mainStream = null;
	
	public static boolean addToBlockChain(ShipmentBean bean) {
		initilizeMainStream();
		BlockChainDatastructure<ShipmentBean> datastructure = mainStream.get(bean.getPrivateKey());
		if (datastructure == null) {
			// for the first time it chain is being created.
			datastructure = new BlockChainDatastructureImpl<>();
			datastructure.addFirst(bean);
			mainStream.put(bean.getPrivateKey(), datastructure);

		} else {
			//already chain is there, adding to last
			datastructure.addLast(bean);
			mainStream.put(bean.getPrivateKey(), datastructure);
		}
		return true;
	}

	private static void initilizeMainStream() {
		if (mainStream == null) {
			mainStream = new LinkedHashMap<String, BlockChainDatastructure<ShipmentBean>>();
			BlockChainDatastructure<ShipmentBean> first = new BlockChainDatastructureImpl<ShipmentBean>();

			ShipmentBean bean = new ShipmentBean();
			bean.setPrivateKey("ShipmentRef1House1");
			bean.setShipmentRefNo("ShipmentRef1");
			bean.setHouseNo("House1");
			bean.setDestinationBranchDepartment("BOM");
			bean.setOriginBranchDepartment("HYD");
			bean.setPol_pod("POD");
			bean.setConsinee("CONSINEE");
			bean.setDate(LocalDateTime.now());
			first.addFirst(bean);
            
			
			
			ShipmentBean bean2 = new ShipmentBean();
			bean2.setPrivateKey("ShipmentRef1House1");
			bean2.setShipmentRefNo("ShipmentRef1");
			bean2.setHouseNo("House1");
			bean2.setDestinationBranchDepartment("BOM");
			bean2.setOriginBranchDepartment("HYD");
			bean2.setPol_pod("POD1");
			bean2.setConsinee("CONSINEE1");
			bean2.setDate(LocalDateTime.now());
			first.addLast(bean2);
			mainStream.put("ShipmentRef1House1", first);

		}
	}
	
	public static LinkedHashMap<String, BlockChainDatastructure<ShipmentBean>> getMainStram() {
		initilizeMainStream();
		return mainStream;
	}

	public static Collection<ShipmentBean> getShipemntBean() {
		initilizeMainStream();
		Set<String> keys = mainStream.keySet();
		ArrayList<ShipmentBean> list = new ArrayList<>();
		for (String key : keys) {
			BlockChainDatastructure<ShipmentBean> shipment = mainStream.get(key);
			ShipmentBean bean = shipment.iterateForward(1);
			list.add(bean);
		}
		return list;
	}

	public static Collection<ShipmentBean> getBlockChains(ShipmentBean bean) {
		initilizeMainStream();
		if (mainStream.get(bean.getPrivateKey()) != null) {
			BlockChainDatastructure<ShipmentBean> dataStructure = mainStream.get(bean.getPrivateKey());
			return dataStructure.getAllNodes();
		}
		return new ArrayList<ShipmentBean>();
	}
	
	
	
	public static ShipmentBean getlastBlockChainForGivenKey(String privateKey,int key) {
		initilizeMainStream();
		if (mainStream.get(privateKey) != null) {
			BlockChainDatastructure<ShipmentBean> dataStructure = mainStream.get(privateKey);
			return dataStructure.iterateBackward(key);
		}
		return new ShipmentBean();
	}
}
