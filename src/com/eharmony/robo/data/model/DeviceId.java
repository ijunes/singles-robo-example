package com.eharmony.robo.data.model;

public class DeviceId {
	DeviceEnum deviceEnum;
	String[] deviceIds = {"4676b827ae38762c", ""};

public DeviceId(DeviceEnum deviceName){
	this.deviceEnum = deviceName;
}

public String getDeviceId(){
	switch(deviceEnum){
	case S3:
		return deviceIds[0];
	case S3CM:
		return deviceIds[1];
	default:
		return null;
	}
}
}