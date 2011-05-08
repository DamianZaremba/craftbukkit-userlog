package userlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserLogFile {
	protected static final Logger log = Logger.getLogger("Minecraft");

	public synchronized boolean init() {
		try {
			File logFile = new File(
				UserLog.logFile
			);

			if (!logFile.exists()) {
				logFile.createNewFile();
			}
			return true;
		} catch (Exception e) {
			log.severe(
				e.getMessage()
			);
		}
		return false;
	}

	public synchronized boolean addUser(String username) {
		BufferedWriter logFile = null;
		try {
			logFile = new BufferedWriter(
				new FileWriter(
					UserLog.logFile,
					true
				)
			);
			logFile.append(username + "\n");
			logFile.close();
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception while writing user to file", e2);
		} finally {
			return true;
		}
	}	

	public synchronized boolean removeUser(String username) {
		FileWriter logFile = null;
		try {
			BufferedReader logFileRead = new BufferedReader(
				new FileReader(
					new File(UserLog.logFile)
				)
			);
			String line = "";
			StringBuilder logFileData = new StringBuilder();

			while ((line = logFileRead.readLine()) != null) {
				if (!line.equalsIgnoreCase(username.toLowerCase())) {
					logFileData.append(line);
					logFileData.append("\n");
				}
			}
			logFileRead.close();

			logFile = new FileWriter(UserLog.logFile);
			logFile.write(logFileData.toString());
			logFile.close();
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception while removing player '" + username + "' from file", e);
		} finally {
			return true;
		}
	}

	public boolean removeAllUsers() {
		FileWriter LogFile = null;
		try {
			LogFile = new FileWriter(UserLog.logFile);
			LogFile.write("");
			LogFile.close();
		} catch (Exception e1) {
			log.log(Level.SEVERE, "Exception while removing all users.", e);
		} finally {
			return true;
		}
	}
}
