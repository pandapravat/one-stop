Ext.require('NOS.model.SingleResult');
Ext.define('NOS.model.ResultGroup', {
    extend: 'Ext.data.Model',
    fields: [
             {
            	 name:'groupName', 
            	 type:'string'
             },
             {
            	 name:'groupEntries', 
            	 type:'auto'
             }
    ]/*,
    hasMany: {
    	name : 'groupEntries', 
    	model: 'NOS.model.SingleResult', 
    	associationKey:'groupEntries' 
    }*/
});