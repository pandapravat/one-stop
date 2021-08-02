Ext.define('NOS.view.EditEntry' ,{
	extend: 'Ext.window.Window',
	alias: 'widget.editentry',
//	store: 'NOS.store.ResultGroupsStore',
	title: 'Edit Entry',
//	layout:'fit',
//	width: 500,
	autoShow: true,
	resizable: false,
	draggable:false,
	initComponent: function() {
		this.items= {
				xtype:'form',
				defaultType: 'textfield',
				layout:'vbox',
				items:[
				       {
				    	   name: 'id',
				    	   fieldLabel: 'ID',
				    	   xtype:'hiddenfield',
				    	   allowBlank:false
				       },
				       {
				    	   fieldLabel: 'Group Name',
				    	   labelSeparator : '',
				    	   name: 'groupName',
				    	   allowBlank:false
				       },
				       {
				    	   fieldLabel: 'Text',
				    	   labelSeparator : '',
				    	   name: 'text',
				    	   allowBlank:false
				       },
				       {
				    	   fieldLabel: 'URL',
				    	   labelSeparator : '',
				    	   name: 'url',
				    	   allowBlank:false
				       },
				       {
				    	   fieldLabel: 'Is Link',
				    	   labelSeparator : '',
				    	   name: 'islink',
				    	   xtype: 'combo',
				    	   displayField: 'name',
				    	   valueField: 'abbr',
				    	   editable:false,
				    	   store: Ext.create('Ext.data.Store',{
				    		   fields: ['abbr', 'name'],
				    		   data : [
				    		           {"abbr":"false", "name":"false"},
				    		           {"abbr":"true", "name":"true"}
				    		           ]
				    	   })
				       },
				       {
				    	   fieldLabel: 'Is Special',
				    	   labelSeparator : '',
				    	   name: 'isSpl',
				    	   xtype: 'combo',
				    	   displayField: 'name',
				    	   valueField: 'abbr',
				    	   editable:false,
				    	   store: Ext.create('Ext.data.Store',{
				    		   fields: ['abbr', 'name'],
				    		   data : [
				    		           {"abbr":"false", "name":"false"},
				    		           {"abbr":"true", "name":"true"}
				    		           ]
				    	   })
				       }
				       ]

		},
		this.buttons = [
		                {
		                	text: 'Save',
		                	action: 'save'
		                },
		                {
		                	text: 'Cancel',
		                	scope: this,
		                	handler: this.close
		                }
		                ];
		this.callParent(arguments);
	},

});