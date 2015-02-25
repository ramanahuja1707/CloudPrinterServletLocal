package com.cloudprinter.services;

import com.cloudprinter.dto.UploadedFilesInfo;
import com.cloudprinter.startup.ObjectifyRegisterService;
import com.googlecode.objectify.Objectify;

public class UploadFileDataSavingService {

	public String FileDataSavingService(UploadedFilesInfo fileInfo) {

		try {
			Objectify ob = ObjectifyRegisterService.registerService();
			UploadedFilesInfo uploadedFileInfo = fileInfo;

			ob.put(uploadedFileInfo);
			return "file_data_saved";

		} catch (Exception e) {
			return "not_saved";
		}

	}
}
