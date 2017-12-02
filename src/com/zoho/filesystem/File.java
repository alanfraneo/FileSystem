package com.zoho.filesystem;

public class File extends FileSystem{

	public void setName(String fname){
		this.name = fname.toLowerCase();
	}

	public File(String fname) throws Exception{
		this.setName(fname);
		this.setProperties(new FileProperties());
		this.setType("File");
		this.setSize(5L);
	}
}
