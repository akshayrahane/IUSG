package com.rmadss.bean;

import java.io.Serializable;

public class SearchForm implements Serializable{

	private String keyword;
	private String wordindex;
	private String indexdescription;
	private int key;
	private String worddocument;
	private String worddocumenttype;
	private String urls;
	private String datatitle;
	private String imagepath;

	private int interfaceid;
	private int keyref;
	private String title;
	private String titledata;
	private String datadescription;

	private int dataindexid;
	private int interfacerefid;
	private String othermetadata;
	private String datadate;
	private String titlehead;
	private int recordscount;
	private int sno;
	private int vcount;
	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getVcount() {
		return vcount;
	}

	public void setVcount(int vcount) {
		this.vcount = vcount;
	}

	private String  user ;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getWordindex() {
		return wordindex;
	}

	public void setWordindex(String wordindex) {
		this.wordindex = wordindex;
	}

	public String getIndexdescription() {
		return indexdescription;
	}

	public void setIndexdescription(String indexdescription) {
		this.indexdescription = indexdescription;
	}

	public String getWorddocument() {
		return worddocument;
	}

	public void setWorddocument(String worddocument) {
		this.worddocument = worddocument;
	}

	public String getDatatitle() {
		return datatitle;
	}

	public void setDatatitle(String datatitle) {
		this.datatitle = datatitle;
	}

	public String getWorddocumenttype() {
		return worddocumenttype;
	}

	public void setWorddocumenttype(String worddocumenttype) {
		this.worddocumenttype = worddocumenttype;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public int getInterfaceid() {
		return interfaceid;
	}

	public void setInterfaceid(int interfaceid) {
		this.interfaceid = interfaceid;
	}

	public int getKeyref() {
		return keyref;
	}

	public void setKeyref(int keyref) {
		this.keyref = keyref;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitledata() {
		return titledata;
	}

	public void setTitledata(String titledata) {
		this.titledata = titledata;
	}

	public String getDatadescription() {
		return datadescription;
	}

	public void setDatadescription(String datadescription) {
		this.datadescription = datadescription;
	}

	public int getDataindexid() {
		return dataindexid;
	}

	public void setDataindexid(int dataindexid) {
		this.dataindexid = dataindexid;
	}

	public int getInterfacerefid() {
		return interfacerefid;
	}

	public void setInterfacerefid(int interfacerefid) {
		this.interfacerefid = interfacerefid;
	}

	public String getOthermetadata() {
		return othermetadata;
	}

	public void setOthermetadata(String othermetadata) {
		this.othermetadata = othermetadata;
	}

	public String getDatadate() {
		return datadate;
	}

	public void setDatadate(String datadate) {
		this.datadate = datadate;
	}

	public String getTitlehead() {
		return titlehead;
	}

	public void setTitlehead(String titlehead) {
		this.titlehead = titlehead;
	}

	public int getRecordscount() {
		return recordscount;
	}

	public void setRecordscount(int recordscount) {
		this.recordscount = recordscount;
	}

}
