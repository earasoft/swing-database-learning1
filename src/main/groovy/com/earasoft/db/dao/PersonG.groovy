package com.earasoft.db.dao;

import java.sql.Connection

import com.earasoft.db.database.manager.DatabaseManager

@interface excludeFullString {}

public class PersonG {
	Integer personId;
	String firstName;
	String lastName;
	String phoneNumber;
	@excludeFullString
	Connection connection;
	@excludeFullString
	DatabaseManager databaseManager;
	
	@Override
	public String toString() {
		return lastName + ", "+ firstName;
	}
	
	public String toStringFull() {
		return "Person [personId = "+ personId +", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + "]";
	}
	
	public void setDatabaseManager(DatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
		this.connection = this.databaseManager.getConnection();
	}
	
	
	static Map mapProperties(def inClass, excludeList = []){
		Map output = [:]
		
		def props = inClass.properties.each { prop, val ->
			if(prop in ["metaClass","class"]) return
		}
		
		props.each{ prop, val ->
			def currentProp = prop.toString()
			
			println excludeList
			println currentProp
			println excludeList.contains(currentProp)
			if(!excludeList.contains(prop))
				if(val!=null){
						if(val instanceof java.lang.Class){
							output[currentProp] = val.getSimpleName()
						}else
							output[currentProp] = val
						
				}else
					output[currentProp] = null
		}
		return  output 
	}
	
	static String toFullStringHelper(def inClass, excludeList = []){
		def mappedProperties = mapProperties(inClass,["connection", "databaseManager"])
		
		//def filteredMappedProperties.each {prop, val ->  }
		
		println mappedProperties
		return "4"
	}
	
	
	
	static main(args){
		def personG = new PersonG(personId:1)
		println personG.toStringFull()
		
		println toFullStringHelper(personG)
		
	}
}
