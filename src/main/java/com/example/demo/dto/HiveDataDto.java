package com.example.demo.dto;
public class HiveDataDto {

	private String dbId;
	private String tableId;
	private String databaseName;
	private String tableName;
	private String dbLocationUri;
	public String getDbId() {
		return dbId;
	}
	public void setDbId(String dbId) {
		this.dbId = dbId;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getDbLocationUri() {
		return dbLocationUri;
	}
	public void setDbLocationUri(String dbLocationUri) {
		this.dbLocationUri = dbLocationUri;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
}
