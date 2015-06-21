/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
 */
package org.magetech.mage.common;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class main {

	/**
	 * Simple Updater downloads the update and replaces the old file starting it back up
	 * @param args array with the download location then the file being updated
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("missing download location and Filename");
			System.exit(4);
		}
		URL webLocation;
		File tempDownloadFile = new File("tempDownloadFile");
		File OldFileName = null;
		try {
			webLocation = new URL(args[0]);
			tempDownloadFile.createNewFile();
			OldFileName = new File(args[1]);
			FileUtils.copyURLToFile(webLocation, tempDownloadFile);
			System.out.println("Done DownLoading");

		} catch (MalformedURLException e1) {
			System.out
					.println("MalformedURL Exception encountered please check the passed URL");
		} catch (IOException e) {
			System.out
					.println("Error with downloading the file starting old version");
			try {
				Runtime.getRuntime().exec("java -jar " + OldFileName);
			} catch (IOException e1) {
				System.err.println("Error with starting old verison");
				e.printStackTrace();
				System.exit(2);
			}
			System.exit(1);
		}

		OldFileName.delete();
		tempDownloadFile.renameTo(OldFileName);

		try {
			Runtime.getRuntime().exec("java -jar " + OldFileName);
		} catch (IOException e) {
			System.err.println("Error with Starting new version");
			e.printStackTrace();
			System.exit(3);
		}
		System.out.println("update successful");
		System.exit(0);
	}

}
