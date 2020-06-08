package comp3350.MovieGoers.business;

import comp3350.MovieGoers.persistence.DataAccessor;
import comp3350.MovieGoers.persistence.Database;

public class DataManager {

	public static final String dbName = "db";
	private static String dbPath = "database/"+dbName;
	private static DataAccessor session;

	public static void startSession(String dbNameIn) {
		System.out.println("Attempting to establish connection to database...");
		if(dbNameIn.equals(dbName)){
			if(session == null) {
				try {
					session = new Database(dbName, dbPath);
				} catch (Exception e) {
					//System.out.println("HERE");
					System.out.println(e.getMessage());
				}
			}
		}else if(dbNameIn.equals(dbName+"Test")){
			if(session == null) {
				try {
					session = new Database();
				} catch (Exception e) {
					//System.out.println("HERE");
					System.out.println(e.getMessage());
				}
			}
		}
		else if(session == null)
		{
			session = null;
		}
	}

    public static DataAccessor getSession() {
        if(session == null) {
            System.out.println("Connection to database has not been established. Exiting.");
            System.exit(1);
        }
        return session;
    }

    public static void closeSession() {
        if(session != null) {
            session.close();
            session = null;
        }
    }
	public static String getDBPathName() {
		if (dbPath == null)
			return dbName;
		else
			return dbPath;
	}

	public static void setDBPathName(String dbPath) {
		System.out.println("Setting DB path to: " + dbPath);
		DataManager.dbPath = dbPath;
	}

	public static void setSession(DataAccessor s){
		session = s;
	}

    
}