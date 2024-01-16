let dataTable;
let dataTableIsInitialized=false;

const dataTableOptions={
	lengthMenu:[1,5,10],
	columnDefs:[
		{className:"centered",targets:[0,1,2,3,4,5]},
	],
	destroy:true,
	pageLength:5,
};

const initDataTable=()=>{
	if(dataTableIsInitialized){
		dataTable.destroy();
	}
	
	dataTable=$("#tabla").DataTable(dataTableOptions);
	dataTableIsInitialized=true;
};

window.addEventListener("load",()=>{
	initDataTable()
});