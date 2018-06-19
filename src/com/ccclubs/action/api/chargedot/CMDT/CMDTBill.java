package com.ccclubs.action.api.chargedot.CMDT;


public class CMDTBill {
	
	private String serialNumber;

	private String cardId;

	private String cardNumber;

	private String chargPileNumber;

	private Double chargStartEnergy;
	
	private Double chargEndEnergy;
	
	private Long chargBeginTime;
	
	private Long chargEndTime;
	
	private Double chargFee;
	
	private Double chargAmount;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getChargPileNumber() {
		return chargPileNumber;
	}

	public void setChargPileNumber(String chargPileNumber) {
		this.chargPileNumber = chargPileNumber;
	}

	public Double getChargStartEnergy() {
		return chargStartEnergy;
	}

	public void setChargStartEnergy(Double chargStartEnergy) {
		this.chargStartEnergy = chargStartEnergy;
	}

	public Double getChargEndEnergy() {
		return chargEndEnergy;
	}

	public void setChargEndEnergy(Double chargEndEnergy) {
		this.chargEndEnergy = chargEndEnergy;
	}

	public Long getChargBeginTime() {
		return chargBeginTime;
	}

	public void setChargBeginTime(Long chargBeginTime) {
		this.chargBeginTime = chargBeginTime;
	}

	public Long getChargEndTime() {
		return chargEndTime;
	}

	public void setChargEndTime(Long chargEndTime) {
		this.chargEndTime = chargEndTime;
	}

	public Double getChargFee() {
		return chargFee;
	}

	public void setChargFee(Double chargFee) {
		this.chargFee = chargFee;
	}

	public Double getChargAmount() {
		return chargAmount;
	}

	public void setChargAmount(Double chargAmount) {
		this.chargAmount = chargAmount;
	}

}
