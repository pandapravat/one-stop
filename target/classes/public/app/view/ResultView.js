Ext.define('NOS.view.ResultView' ,{
	extend: 'Ext.grid.Panel',
	xtype: 'grouped-grid',
	alias:'widget.resultView',
	id:'resultGrid',
	features: [
	           {
	        	   ftype:'grouping', 
	        	   startCollapsed:true,
	        	   hideGroupedHeader:true,
	        	   groupHeaderTpl: '{name:uppercase}'
	           }
	           ],
	
	alias:'widget.results',
	title: "<div style='text-align:center; font-size:15px'>Nielsen One Stop</div>",
	initComponent : function() {
		
		console.log("reached in view initialise");
		results = Ext.create('NOS.store.ResultGroupsStore');
		this.store = results;
		this.columns = [
    	                {
    	                	header: 'Text', 
    	                	dataIndex: 'text', 
    	                	flex:1,
    	                	renderer:function(value, metaData, record){
    	                		var link = record.get('islink');
    	                		var url = record.get('url');
    	                		if(link) {
    	                			return '<a target="_blank" href="' + url + '">'+value+'</a>';
    	                		} else {
    	                			return value;
    	                		}
    	                		
    	                	}
    	                },
    	                {
    	                	header: 'Actions', 
    	                	xtype:'actioncolumn',
    	                	flex:1,
    	                	items: [{
    	                        icon: 'images/view.png',  // Use a URL in the icon config
    	                        tooltip: 'Show Details',
    	                        iconCls: 'action_img',
    	                        handler: function(view, rowIndex, colIndex, item, e, record, row) {
//    	                            var rec = grid.getStore().getAt(rowIndex);
    	                        	var view = Ext.widget('viewentry');
    	                        	view.down('textareafield').setRawValue(record.get('url'));
    	                        	view.show();
    	                        },

    	                    },{
    	                        icon: 'images/edit.png',
    	                        tooltip: 'Edit Details',
    	                        iconCls: 'action_img',
    	                        handler: function(grid, rowIndex, colIndex) {
    	                        	var record1 = grid.getStore().getAt(rowIndex);
    	                        	var view = Ext.widget('editentry');
    	                        	view.down('form').loadRecord(record1);
    	                        }
    	                    },{
    	                        icon: 'images/delete.png',
    	                        tooltip: 'Delete Details',
    	                        handler: function(record) {
    	                        	Ext.Msg.alert("Delete Clicked", "No Magic Here");
    	                        }
    	                    }]
    	                },
    	                
    	                {header: 'Group Name', dataIndex: 'groupName', flex: 1},
    	            ];
		results.load();
		console.log("End in view initialise");
        this.callParent(arguments);
	}
});