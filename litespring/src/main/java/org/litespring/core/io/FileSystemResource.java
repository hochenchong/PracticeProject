package org.litespring.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.litespring.util.Assert;

public class FileSystemResource implements Resource {

	private String path;
	private File file;
	
	public FileSystemResource(String path) {
		Assert.notNull(path, "Path must not be null");
		this.path = path;
		this.file = new File(path);
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(this.file);
	}

	@Override
	public String getDescription() {
		return "file [" + this.file.getAbsolutePath() + "]";
	}

}
