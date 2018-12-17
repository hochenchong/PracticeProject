package org.litespring.beans;

public class PropertyValue {
	private final String name;
	private final Object value;
	// 是否是可以转换的对象
	private boolean converted = false;
	// 转换后的对象
	private Object convertedValue;
	
	public PropertyValue(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public Object getValue() {
		return value;
	}
	public synchronized boolean isConverted() {
		return converted;
	}
	public synchronized Object getConvertedValue() {
		return convertedValue;
	}
	public synchronized void setConvertedValue(Object convertedValue) {
		this.converted = true;
		this.convertedValue = convertedValue;
	}
}
