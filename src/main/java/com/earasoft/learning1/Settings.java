package com.earasoft.learning1;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Settings {
	private static final Logger logger = LoggerFactory.getLogger(Settings.class);

    /**
     * Define the database to use for persistence
     */
    public static final String DATABASE_BACKEND_KEY = "database";
    public static final String DATABASE_BACKEND_DEFAULT = "sqlite";
    
    
    public static String getPath(File dir) {
        return dir.getAbsolutePath() + File.separator;
    }


    static boolean existsFile(String file) {
        return (new File(file)).isFile();
    }
}
