package com.earasoft.db.dao.impl

import java.sql.Connection
import java.util.Map;

import com.earasoft.db.dao.PersonG
import com.earasoft.db.dao.helpers.GBase
import com.earasoft.db.dao.helpers.GHelper
import com.earasoft.db.dao.helpers.IndividualDoa
import com.earasoft.db.database.manager.DatabaseManager
import com.earasoft.db.security.RolesE

public class PersonDoaG extends GBase implements IndividualDoa, PersonG{
	public String tableName = "person"
	
	public Integer personId
	public String firstName
	public String lastName
	public String phoneNumber
	public String address
	public String city
	public String state
	public String zip
	public String position
	public String team
	public String role
	public String password
	
	@Override
	public String toString() {
		return lastName + ", "+ firstName
	}
	
	static updateSqlGenerator(def inClass){
		def mapProps = GHelper.mapProperties(inClass, ["connection", "databaseManager"])
		
		println mapProps
		StringBuilder sql = new StringBuilder()
		
		
		sql.append("UPDATE " + mapProps["tableName"].value + " SET ")
		
		
		def properties = mapProps.properties
		def flagFirst = true
		
		properties.keySet().each {			
			
			def valueA
			if(properties[it]["type"] instanceof Number)
				valueA=it + "=" + properties[it].value
			else
				valueA = it + "='" + ((properties[it].value == null) ? '' : properties[it].value)  + "'"
			
			if(flagFirst){
				flagFirst=false
				
				sql.append(valueA)
			}else{
				sql.append(", " + valueA)
			}
			
		}
		sql.append(" WHERE ")
		
		def ids = mapProps.ids
		flagFirst = true
		
		ids.keySet().each {
			
			def valueA
			
			if(ids[it]["type"] == Integer)
				valueA=it + "=" +ids[it].value
			else
				valueA = it + "='" + ids[it].value + "'"
			
			if(flagFirst){
				flagFirst=false
				
				sql.append(valueA)
			}else{
				sql.append(", " + valueA)
			}
			
		}
		return sql.toString()
	}
	
	public String updateSql(){
		updateSqlGenerator(this)
	}
	
	public String toStringFull() {
		return GHelper.mapProperties(this,["connection", "databaseManager"]).toString()
	}
	
	static main(args){
		def personG = new PersonDoaG(personId:1,role:RolesE.ADMIN)
		//println personG.toStringFull()
		
		println personG.updateSql()
 
		
		//println GHelper.mapProperties(personG,["connection", "databaseManager"])
//		PersonDoaG.class.properties.each { prop, val ->
//			println prop
//			println val
//			println "++++++++++"
//		}
		

		
		
		
	}
	

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map getPropertiesMap() {
		return GHelper.mapProperties(this, ["connection", "databaseManager"])
	}
}
