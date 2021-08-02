Ext.define('NOS.store.ResultGroupsStore', {
	extend: 'Ext.data.Store',
//	requires : ['NOS.model.ResultGroup', 'NOS.model.SingleResult'], 
	model: 'NOS.model.SingleResult',
	groupField: 'groupName',
//	autoLoad: true,
	 proxy: {
			type: 'ajax',
			url: 'ext/showResult.nos',
			reader: {
				type: 'json'
			}
		}
});