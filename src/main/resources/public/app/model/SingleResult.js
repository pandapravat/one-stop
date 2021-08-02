Ext.define('NOS.model.SingleResult',{
	extend: 'Ext.data.Model',
	fields: [
	         {name:'id', type:'string'},
	         {name:"text", type:"string"},
	         {name:"url", type:"string"},
	         {name:"groupName", type:"string"},
	         {name:"islink", type:"boolean"},
	         {name:"isSpl", type:"boolean"}
	         ]
//	belongsTo: 'NOS.model.ResultGroup'
});