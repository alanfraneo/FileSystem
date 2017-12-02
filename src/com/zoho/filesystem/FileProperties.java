package com.zoho.filesystem;

public class FileProperties extends FSProperties{
	private boolean systemFile;
	private boolean executable;
	private boolean shared;

	public boolean isSystemFile() {
		return systemFile;
	}

	public void setSystemFile(boolean systemFile) {
		this.systemFile = systemFile;
	}

	public boolean isExecutable() {
		return executable;
	}

	public void setExecutable(boolean executable) {
		this.executable = executable;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public FileProperties(){
		this.setReadOnly(false);
		this.setSystemFile(false);
		this.setExecutable(false);
		this.setShared(false);
		this.setHidden(false);
	}

	public String toString(){
		String sb = "File Properties: \n" +
				"    Write properties: " + (this.isReadOnly() ? "Read-only" : "Read-write") + "\n" +
				"    System file: " + this.isSystemFile() + "\n" +
				"    Sharing Status: " + (this.isShared() ? "Shared" : "Not shared") + "\n" +
				"    Executable: " + this.isExecutable() + "\n" +
				"    Visibility: " + this.isHidden() + "\n";
		return sb;
	}



}
