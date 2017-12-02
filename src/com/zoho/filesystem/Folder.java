package com.zoho.filesystem;

import java.nio.file.FileStore;
import java.util.Collections;
import java.util.HashMap;

public class Folder extends FileSystem{
	private HashMap<String, FileSystem> contents;
	String type = "Folder";

	public void setName(String fName){
		this.name = fName.toUpperCase();
	}

	public Folder(String fName){
		this.setName(fName);
		this.setType("Folder");
		this.properties = new FolderProperties();
		this.contents = new HashMap<String, FileSystem>();
	}

	public Long getSize(){
		Long folderSize = 0L;
		for (FileSystem fs: contents.values()) {
			folderSize += fs.getSize();
		}
		return folderSize;
	}

	public void setSize(Long size) throws Exception{
		throw new Exception("Cannot manually set size for a folder");
	}

	public File getFile(String name){
		if (contents.keySet().contains(name)){
			if (contents.get(name).getType().equals("File")){
				return (File) contents.get(name);
			}
			else {
				return null;
			}
		}
		else{
			return null;
		}
	}

	public Folder getSubDirectory(String name){
		if (contents.keySet().contains(name)){
			if (contents.get(name).getType().equals("Folder")){
				return (Folder) contents.get(name);
			}
			else {
				return null;
			}
		}
		else{
			return null;
		}
	}

	public String getInfo(){
		StringBuilder sb = new StringBuilder();
		sb.append("Folder name: ").append(this.getName()).append("\n");
		sb.append("Size: ").append(this.getSize()).append(" bytes\n");
		sb.append(this.getProperties()).append("\n");
		if (contents.size() > 0){
			int filecount =0, foldercount = 0;
			for (FileSystem fs: contents.values()){
				if (fs.getType().equals("File")){
					filecount++;
				}else if (fs.getType().equals("Folder")){
					foldercount++;
				}
			}
			sb.append("File count: ").append(filecount).append("\n");
			sb.append("SubFolders count: ").append(foldercount).append("\n");
		}else{
			sb.append("Folder is empty");
		}
		return sb.toString();
	}

	public String listEverything(){
		return listContentsMain(true,false, true);
	}

	public String listAllContents(){
		return listContentsMain(true, false, false);
	}

	public String listContents(){
		return listContentsMain(false, false, false);
	}

	public String listDetails(){
		return listContentsMain(true, true, false);
	}

	private String listContentsMain(boolean includeHidden, boolean includeDetails, boolean includeDeleted){
		if (contents.size() > 0){
			StringBuilder sb = new StringBuilder();
			for (String key : contents.keySet()){
				FileSystem item = contents.get(key);
				if ((includeHidden && item.getProperties().isHidden() && !item.getProperties().isDeleted())
						|| (!item.getProperties().isHidden() && !item.getProperties().isDeleted())
						|| (includeDeleted && item.getProperties().isDeleted())){
					sb.append(item.getName()).append(" - ").append(item.getType());
					sb.append(" -------- ").append(item.getSize()).append(" bytes");
					if (item.getProperties().isDeleted()){
						sb.append(" (deleted)");
					}
					sb.append("\n");

				}
				if (includeDetails){
					sb.append(item.getProperties());
					sb.append("\n");
				}
			}
			return sb.toString();
		}
		else{
			return "Folder is empty.\n" +
					"Get started by using the below commands:\n" +
					"    new file: addFile <filename>\n"+
					"    new folder: mkdir <foldername>";
		}
	}

	public String addItemtoFolder(FileSystem fs){
		if (contents.containsKey(fs.getName())){
			return fs.getType() + " with that name already exists! Try a different name";
		}
		else {
			contents.put(fs.getName(), fs);
			return fs.getType()+ " added successfully";
		}
	}

	public String removeItemFromFolder(String name){
		if (contents.containsKey(name)){
			contents.get(name).getProperties().setDeleted(true);
			return contents.get(name).getType() + "'"+name+"'" + " deleted successfully";
		}
		else {
			return "File/Folder: '"+ name + "' does not exist";
		}
	}
}
