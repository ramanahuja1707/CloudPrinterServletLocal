package com.cloudprinter.enums;

public enum NotificationStatus {
TRUE("true"), FALSE("false");
private  String notificationStatus;

private NotificationStatus(String NotificationStatus) {
	this.notificationStatus = notificationStatus;
}

public String getNotificationStatus() {
	return notificationStatus;
}

}
