package com.hk.DTO.Mssql;

public class A {

	private String Itemtitle;
	private String ItemID;
	
	
	
	public A(String itemtitle, String itemID) {
		super();
		Itemtitle = itemtitle;
		ItemID = itemID;
	}
	public String getItemtitle() {
		return Itemtitle;
	}
	public void setItemtitle(String itemtitle) {
		Itemtitle = itemtitle;
	}
	public String getItemID() {
		return ItemID;
	}
	public void setItemID(String itemID) {
		ItemID = itemID;
	}
	

	@Override
	public String toString() {
		return "A [Itemtitle=" + Itemtitle + ", ItemID=" + ItemID + "]";
	}
}
