package com.zoho.filesystem;

public class FileSystem {
	String name;
	String type;
	Long size;
	FSProperties properties;

	public String getName(){
		return name;
	}

	public String getType(){
		return type;
	}

	protected void setType(String type){
		this.type = type;
	}

	public void setName(String fName){
		name = fName;
	}

	public FSProperties getProperties(){
		return properties;
	}

	public void setProperties(FSProperties props){
		this.properties = props;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) throws Exception{
		this.size = size;
	}
}
