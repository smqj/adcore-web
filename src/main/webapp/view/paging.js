   
    // Initialize application
    function loadGanttData(start, end, urlStr) {  
        Ext.QuickTips.init();      
    
        //var start = new Date(2013,0,1),
        //end = Sch.util.Date.add(start, Sch.util.Date.WEEK, 52);
        
        var store = new Ext.ux.maximgb.tg.AdjacencyListStore({
            defaultExpanded : true,
            proxy: new Ext.data.HttpProxy({   
                      url: urlStr,//'/pro/ProblemGanttController?choice=3',   
                      method: 'GET'  
            }), 
    
          //proxy: new Ext.ux.data.PagingMemoryProxy(Data),
//new Ext.ux.data.PagingMemoryProxy('/pro/ProblemGanttController?choice=3'),//'/pro/ProblemGanttController?choice=3',taskData
		    reader: new Ext.data.JsonReader({idProperty : 'Id',root: 'data'}, 
		    	[
                    // Mandatory fields
     	            {name:'Id'},
                    {name:'Name', type:'string'},
                    {name:'StartDate', type : 'date', dateFormat:'c'},
                    {name:'EndDate', type : 'date', dateFormat:'c'},
                    {name:'PercentDone'},                   
                    {name:'IsLeaf', type: 'bool'}
                ]
            )
        });
        
        var g = new Sch.gantt.GanttPanel({
            height : 400,
            width: 1200,
            renderTo : 'main',
            leftLabelField : 'Name',
            highlightWeekends : false,
            showTodayLine : false,
            enableDependencyDragDrop : false,
            
            startDate : start, 
            endDate : end, 
            viewPreset : 'weekAndMonth',
            
            // Setup your static columns
            columns : [
                {
                    header : 'Tasks', 
                    sortable:true, 
                    dataIndex : 'Name', 
                    locked : true,
                    width:250
                }
            ],
            taskStore : store,
            stripeRows : true,
            //plugins : [new Sch.plugins.Pan()],
            
            // paging bar on the bottom
            bbar: new Ext.PagingToolbar({
                pageSize: 10,
                store: store,
                displayInfo: true,
                displayMsg: '当前显示 {0} - {1} of {2}',
                emptyMsg: "无内容可显示"
            })
        });
        
        store.load({
            params : {
                start : 0,
                limit : 10
            }
        });
    };


