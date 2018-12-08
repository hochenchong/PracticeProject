package org.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;

public interface Resource {
	/**
	 * 获取读取到的 IO 流数据
	 * @return
	 * @throws IOException
	 */
	public InputStream getInputStream() throws IOException;
	
	/**
	 * 获取资源描述
	 * @return
	 */
	public String getDescription();
}
