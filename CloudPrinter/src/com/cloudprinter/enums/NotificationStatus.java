package com.cloudprinter.enums;

public enum NotificationStatus {
	TRUE("true"), FALSE("false");
	private String notificationStatus;

	private NotificationStatus(String NotificationStatus) {
		this.notificationStatus = NotificationStatus;
	}

	public String getNotificationStatus() {
		return notificationStatus;
	}

}
