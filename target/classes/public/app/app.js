Ext.require('Ext.container.Viewport');

Ext.Loader.setConfig({
	enabled: true,
	paths: {
		'NOS': 'app'
	}
});



Ext.application({
	name: 'NOS',
	controllers: ['NOS.controller.NosUiController'],
	
	launch: function() {
		console.log("Starting app launch");
		Ext.create('Ext.container.Viewport', {
			layout: 'fit',
			items: [{
				xtype: 'results'
			}]
		});
		console.log("End of launch application");
	}
});

