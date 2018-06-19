package com.ccclubs.entity;

/**
 * 发票信息
 * @author uiu
 *
 */
public class Invoice {

	Long member;//会员
	Double consume;//总保证金消费额
	Double already;//已开票额
	Double viable;//可开票额
	
	public Long getMember() {
		return member;
	}
	public void setMember(Long member) {
		this.member = member;
	}
	public Double getConsume() {
		return consume;
	}
	public void setConsume(Double consume) {
		this.consume = consume;
	}
	public Double getViable() {
		return viable;
	}
	public void setViable(Double viable) {
		this.viable = viable;
	}
	public Double getAlready() {
		return already;
	}
	public void setAlready(Double already) {
		this.already = already;
	}
	
}
