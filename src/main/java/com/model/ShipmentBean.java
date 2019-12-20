package com.model;

import java.time.LocalDateTime;

public class ShipmentBean {
	private int versionId;
    private String privateKey;
	private String shipmentRefNo;
	private String houseNo;
	private String originBranchDepartment;
	private String destinationBranchDepartment;
	private String eta_etd;
	private String pol_pod;
	private String shippers;
	private String consinee;
	private LocalDateTime date;

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getShipmentRefNo() {
		return shipmentRefNo;
	}

	public void setShipmentRefNo(String shipmentRefNo) {
		this.shipmentRefNo = shipmentRefNo;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getOriginBranchDepartment() {
		return originBranchDepartment;
	}

	public void setOriginBranchDepartment(String originBranchDepartment) {
		this.originBranchDepartment = originBranchDepartment;
	}

	public String getDestinationBranchDepartment() {
		return destinationBranchDepartment;
	}

	public void setDestinationBranchDepartment(String destinationBranchDepartment) {
		this.destinationBranchDepartment = destinationBranchDepartment;
	}

	public String getEta_etd() {
		return eta_etd;
	}

	public void setEta_etd(String eta_etd) {
		this.eta_etd = eta_etd;
	}

	public String getPol_pod() {
		return pol_pod;
	}

	public void setPol_pod(String pol_pod) {
		this.pol_pod = pol_pod;
	}

	public String getShippers() {
		return shippers;
	}

	public void setShippers(String shippers) {
		this.shippers = shippers;
	}

	public String getConsinee() {
		return consinee;
	}

	public void setConsinee(String consinee) {
		this.consinee = consinee;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}