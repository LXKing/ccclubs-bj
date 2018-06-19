package com.ccclubs.model;

import java.util.List;
import java.util.Map;

import com.lazy3q.lang.*;
import com.lazy3q.web.helper.$;

/**
 * SrvLimited entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@namespace("permissions/limit")
public class SrvLimited implements java.io.Serializable
{

	// Fields

	private @caption("编号") @column("sl_id") Long slId;

	//关联项目(srv_project)表的主键
	private @caption("项目ID") @column("sl_project_id") Long slProjectId;

	//关联用户(srv_user) 或 组(srv_group) 的主键，
	private @caption("用户或组ID") @column("sl_actor_id") Long slActorId;

	//决定上面的字段关联哪个表，0:srv_group   1:srv_user
	private @caption("权限者类型") @column("sl_actor_type") Integer slActorType;

	//位运算(1|2|4|8|....),1代码查看，2代表添加，4代表编辑，8代码删除，16，32，64，128，256....后面这些代表扩展权限
	private @caption("权限值") @column("sl_limit") Integer slLimit;

	// Constructors

	/** default constructor */
	public SrvLimited()
	{
	}

	/** full constructor */
	public SrvLimited(Long slProjectId, Long slActorId, Integer slActorType,
			Integer slLimit)
	{
		this.slProjectId = slProjectId;
		this.slActorId = slActorId;
		this.slActorType = slActorType;
		this.slLimit = slLimit;
	}
	
	/**
	 * 获取所有权限
	 * @return
	 */
	public static @api List<SrvLimited> list(Map params,Integer size){
		return getSrvLimitedList(params,size);
	}
	/**
	 * 获取所有权限
	 * @return
	 */
	public static @api List<SrvLimited> getSrvLimitedList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvLimitedService srvLimitedService = $.GetSpringBean("srvLimitedService");
		return srvLimitedService.getSrvLimitedList(params, size);
	}

	// Property accessors

	public Long getSlId()
	{
		return this.slId;
	}

	public void setSlId(Long slId)
	{
		this.slId = slId;
	}

	public Long getSlProjectId()
	{
		return this.slProjectId;
	}

	public void setSlProjectId(Long slProjectId)
	{
		this.slProjectId = slProjectId;
	}

	public Long getSlActorId()
	{
		return this.slActorId;
	}

	public void setSlActorId(Long slActorId)
	{
		this.slActorId = slActorId;
	}

	public Integer getSlActorType()
	{
		return this.slActorType;
	}

	public void setSlActorType(Integer slActorType)
	{
		this.slActorType = slActorType;
	}

	public Integer getSlLimit()
	{
		return this.slLimit;
	}

	public void setSlLimit(Integer slLimit)
	{
		this.slLimit = slLimit;
	}
}