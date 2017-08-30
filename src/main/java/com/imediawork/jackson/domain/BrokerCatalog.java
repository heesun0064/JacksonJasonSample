package com.imediawork.jackson.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BrokerCatalog
{
	private int allocationType;
	private int assignedCount;
	private int availableAssignedCount;
	private int availableCount;
	private int availableUnassignedCount;
	private String name;
	private String uuid;
	private int usedCount;
	private String zoneName;

	@JsonProperty("AllocationType")
	public int getAllocationType()
	{
		return allocationType;
	}

	@JsonProperty("AllocationType")
	public void setAllocationType(int allocationType)
	{
		this.allocationType = allocationType;
	}

	@JsonProperty("AssignedCount")
	public int getAssignedCount()
	{
		return assignedCount;
	}

	@JsonProperty("AssignedCount")
	public void setAssignedCount(int assignedCount)
	{
		this.assignedCount = assignedCount;
	}

	@JsonProperty("AvailableAssignedCount")
	public int getAvailableAssignedCount()
	{
		return availableAssignedCount;
	}

	@JsonProperty("AvailableAssignedCount")
	public void setAvailableAssignedCount(int availableAssignedCount)
	{
		this.availableAssignedCount = availableAssignedCount;
	}

	@JsonProperty("AvailableCount")
	public int getAvailableCount()
	{
		return availableCount;
	}

	@JsonProperty("AvailableCount")
	public void setAvailableCount(int availableCount)
	{
		this.availableCount = availableCount;
	}

	@JsonProperty("AvailableUnassignedCount")
	public int getAvailableUnassignedCount()
	{
		return availableUnassignedCount;
	}

	@JsonProperty("AvailableUnassignedCount")
	public void setAvailableUnassignedCount(int availableUnassignedCount)
	{
		this.availableUnassignedCount = availableUnassignedCount;
	}

	@JsonProperty("Name")
	public String getName()
	{
		return name;
	}

	@JsonProperty("Name")
	public void setName(String name)
	{
		this.name = name;
	}

	@JsonProperty("UUID")
	public String getUuid()
	{
		return uuid;
	}

	@JsonProperty("UUID")
	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	@JsonProperty("UsedCount")
	public int getUsedCount()
	{
		return usedCount;
	}

	@JsonProperty("UsedCount")
	public void setUsedCount(int usedCount)
	{
		this.usedCount = usedCount;
	}

	@JsonProperty("ZoneName")
	public String getZoneName()
	{
		return zoneName;
	}

	@JsonProperty("ZoneName")
	public void setZoneName(String zoneName)
	{
		this.zoneName = zoneName;
	}
}
