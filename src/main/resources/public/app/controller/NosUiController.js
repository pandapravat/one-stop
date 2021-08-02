Ext.define('NOS.controller.NosUiController', {
	extend : 'Ext.app.Controller',
	stores: ['NOS.store.ResultGroupsStore'],
	views: ['NOS.view.ViewEntry', 'NOS.view.EditEntry', 'NOS.view.ResultView'],
	init: function() {

		this.control({
			'editentry button[action=save]': {
				click:this.updateEntry
			}
		});
	},
	updateEntry: function(but) {
		 but.up('window').setLoading(true);
		Ext.Ajax.request({
			url: 'ext/processUpdate.nos',
			params: but.up('window').down('form').getValues(),
			method:'POST',
			success: function() {
				 but.up('window').setLoading(true);
				but.up('window').close();
				Ext.Msg.show(
						{
							title:'Status', 
							msg: 'Changes saved successfully.',
							buttons:Ext.Msg.OK,
							fn:function() {
								console.log('Clickeed');
								window.location.reload();
							}
						});
			},
			failure: function(response) {
				 but.up('window').setLoading(true);
				alert('Failure' + response.statusText);
			}

		});

		console.log('Reached in controller for update!!');
	} 

});
