package com.augmenter.app.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * @author Varun Shrivastava
 *
 */
public class ReaderUtil {
	
	private static final Logger logger = Logger.getLogger(ReaderUtil.class);
	
	private static final String SQL_FOLDER_NAME  =  "sql";
	private static final String FOLDER_SEPARATOR  = "/";
	private static final String SQL_EXT = ".sql";

	private ReaderUtil() {}

	/**
	 * Reads SQL file from the classpath  sql folder
	 * @param sqlFileName
	 * @return
	 */
	public static String readSQLFile(String sqlFileName) {
		String line  =  "";
		StringBuilder  sb  = new StringBuilder("");
		String fullFileName = SQL_FOLDER_NAME.concat(FOLDER_SEPARATOR).concat(sqlFileName).concat(SQL_EXT);
		logger.debug(fullFileName);
		String filepath = ReaderUtil.class.getClassLoader().getResource(fullFileName).getPath();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(filepath)))){
			while (null != (line = br.readLine()))
				sb.append(line)
				.append(System.lineSeparator());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return sb.toString();
	}
	
	
}
