package org.litespring.test.v1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

public class ResourceTest {

	@Test
	public void testClassPathResource() throws IOException {
		Resource resource = new ClassPathResource("petstore-v1.xml");
		
		InputStream is = null;
		
		try {
			is = resource.getInputStream();
			// 简单测试
			Assert.assertNotNull(is);
		} finally {
			if (null != is) {
				is.close();
			}
		}
	}
	
	@Test
	public void testFileSystemResource() throws IOException {
		// 获取 petstore-v1.xml 在本机的绝对路径
		/*
		String filePath = this.getClass().getResource("/petstore-v1.xml").getFile().toString();
		Resource resource = new FileSystemResource(filePath);
		*/
		
		// 使用相对路径获取 petstore-v1.xml 文件
		Resource resource = new FileSystemResource("src/test/resources/petstore-v1.xml");
		
		InputStream is = null;
		
		try {
			is = resource.getInputStream();
			System.out.println(resource.getDescription());
			// 简单测试
			Assert.assertNotNull(is);
		} finally {
			if (null != is) {
				is.close();
			}
		}
	}

}
