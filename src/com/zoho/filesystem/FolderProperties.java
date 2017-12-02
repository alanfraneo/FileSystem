package com.zoho.filesystem;

public class FolderProperties extends FSProperties{

	private boolean systemFolder;

	public boolean isSystemFolder() {
		return systemFolder;
	}

	public void setSystemFolder(boolean systemFolder) {
		this.systemFolder = systemFolder;
	}

	public FolderProperties(){
		this.setReadOnly(false);
		this.setSystemFolder(false);
		this.setHidden(false);
	}

	public String toString(){
		return "Folder Properties: \n" +
				"    Write properties: " + (this.isReadOnly() ? "Read-only" : "Read-write")+ "\n" +
				"    System folder: " + this.isSystemFolder() + "\n" +
				"    Visibility: " + (this.isHidden() ? "Hidden" : "Visible");
	}

}
