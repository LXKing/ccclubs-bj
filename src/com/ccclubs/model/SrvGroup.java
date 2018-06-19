package com.ccclubs.model;

import com.lazy3q.lang.*;

/**
 * SrvGroup entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@namespace("permissions/group")
public @caption("组") class  SrvGroup implements java.io.Serializable
{

	// Fields

	private @caption("编号") @column("sg_id") Long sgId;

	private @caption("组名") @column("sg_name") String sgName;
	
	private @caption("所属用户") @column("sg_parent_id") Long sgParentId;
	
	private @caption("状态") @column("sg_status") Integer sgStatus;

	// Constructors

	/** default constructor */
	public SrvGroup()
	{
	}
	
	// Property accessors

	public Long getSgId()
	{
		return this.sgId;
	}

	public void setSgId(Long sgId)
	{
		this.sgId = sgId;
	}

	public String getSgName()
	{
		return this.sgName;
	}

	public void setSgName(String sgName)
	{
		this.sgName = sgName;
	}

	public Long getSgParentId()
	{
		return sgParentId;
	}

	public void setSgParentId(Long sgParentId)
	{
		this.sgParentId = sgParentId;
	}

	public Integer getSgStatus()
	{
		return sgStatus;
	}

	public void setSgStatus(Integer sgStatus)
	{
		this.sgStatus = sgStatus;
	}

}