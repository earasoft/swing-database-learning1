package com.earasoft.db.dao.helpers

import java.util.Map;

class GHelper {

	public static Map mapProperties(def inClass, excludeList = []){
		Map output = [:]
		
		output["ids"] = [:]
		output["properties"]  = [:]
		
//		def props = inClass.properties.each { prop, val ->
//			if(prop in ["metaClass","class"]) return
//		}
			
		def props = [:]
		inClass.class.getDeclaredFields().each{
			if(!it.getName().contains('$')){
				
				int mod = it.getModifiers();
				
				if(mod==1){
					def fieldName=it.getName()
					//println inClass.fieldName
					
					def meta = ['value':it.get(inClass), 'type': it.getType()]
					props[fieldName] = meta
				}
			}
		}
		
		
		def propOrId = { outputA, prop, val ->
			if(prop.contains("Id")){
				outputA["ids"][prop] = val
			}else if(prop.equals("tableName")){
				outputA[prop] = val
			}else{
				outputA["properties"][prop] = val
			}
		}
		props.each{ prop, val ->
			def currentProp = prop.toString()
			if(!excludeList.contains(prop))
				if(val != null){
						if(val instanceof java.lang.Class){
							output[currentProp] = val.getSimpleName().toString()
						}else
							propOrId(output,currentProp,val)
				}else
					propOrId(output,currentProp,null)
					
		}
		return  output
	}
	
	
}
