Ext.define('NOS.view.ViewEntry' ,{
	extend: 'Ext.window.Window',
	alias: 'widget.viewentry',
	title:"View Details",
	width:400,
	initComponent: function() {
		this.items = [
		              {
		            	  xtype: 'panel',
		            	  layout: 'fit',
		            	  items: [
		            	          {
		            	        	  xtype: 'textareafield',
		            	        	  name : 'name',
		            	          }

		            	          ]
		              }
		              ];

		this.buttons = [
		                {
		                	text: 'Okay',
		                	scope:this,
		                	handler: this.close
		                }
		                ];

		this.callParent(arguments);
	}

});