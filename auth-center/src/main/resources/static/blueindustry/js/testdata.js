var demo_tasks = {
	"data":[
		{"id":11, "text":"并行工序组", "start_date":"", "duration":"", "progress": 1, "open": true},
		{"id":1, "text":"并行工序组", "start_date":"", "duration":"", "progress": 1, "open": true},

		{"id":2, "text":"底盘", "start_date":"", "duration":"", "parent":"1", "progress":1, "open": true},
		{"id":3, "text":"动力", "start_date":"", "duration":"", "parent":"1", "progress": 1, "open": true},
		{"id":4, "text":"车架", "start_date":"", "duration":"", "parent":"1", "progress": 1, "open": true},
		{"id":5, "text":"1号机台", "start_date":"01-04-2018 02:00", "duration":"3", "parent":"2", "progress": 0.5, "open": true},
		{"id":6, "text":"2号机台", "start_date":"01-04-2018 09:00", "duration":"1", "parent":"2", "progress": 1, "open": true},
		{"id":7, "text":"1号车间", "start_date":"02-04-2018 02:00", "duration":"2", "parent":"3", "progress": 0.2, "open": true},
		{"id":8, "text":"1号车间", "start_date":"01-04-2018 09:00", "duration":"3", "parent":"4", "progress": 0.5, "open": true},
		{"id":9, "text":"2号车间", "start_date":"01-04-2018 09:00", "duration":"1", "parent":"4", "progress": 0.6, "open": true},
		{"id":10, "text":"3号车间", "start_date":"01-04-2018 09:00", "duration":"2", "parent":"4", "progress": 0.5, "open": true},
		
		{"id":12, "text":"喷图", "start_date":"01-04-2018 02:00", "duration":"3", "parent":"11", "progress": 0.45, "open": true},
		{"id":13, "text":"总装", "start_date":"", "duration":"", "parent":"11", "progress": 1, "open": true},
		{"id":14, "text":"入库", "start_date":"03-04-2018 06:00", "duration":"2", "parent":"11", "progress": 0, "open": true},
		{"id":15, "text":"车架", "start_date":"", "duration":"", "parent":"11", "progress": 1, "open": true},
		{"id":16, "text":"包铁皮", "start_date":"01-04-2018", "duration":"1", "parent":"11", "progress": 1, "open": true},

		{"id":17, "text":"1号机台", "start_date":"02-04-2018", "duration":"2", "parent":"13", "progress": 0.2, "open": true},
		{"id":18, "text":"2号机台", "start_date":"02-04-2018 06:00", "duration":"3", "parent":"13", "progress": 0.1, "open": true},
		{"id":19, "text":"3号机台", "start_date":"02-04-2018", "duration":"4", "parent":"13", "progress": 0.1, "open": true},
		{"id":20, "text":"4号机台", "start_date":"02-04-2018", "duration":"4", "parent":"13", "progress": 0.1, "open": true},
		{"id":21, "text":"1号机台", "start_date":"01-04-2018 14:00", "duration":"4", "parent":"15", "progress": 0.2, "open": true},
		{"id":22, "text":"2号机台", "start_date":"01-04-2018 14:00", "duration":"4", "parent":"15", "progress": 0.2, "open": true},
		{"id":23, "text":"3号机台", "start_date":"01-04-2018 14:00", "duration":"5", "parent":"15", "progress": 0.1, "open": true,"priority": "delay"}
	]
};
// var demo_tasks = {
// 	"data": [
// 		{"id": 11, "text": "并行工序组", "start_date": "", "duration": "", "progress": 0.6, "open": true},
// 		{"id":12, "text":"底盘", "start_date":"03-04-2018", "duration":"5", "parent":"11", "progress": 1, "open": true},
// 		{"id":13, "text":"车架", "start_date":"", "duration":"", "parent":"11", "progress": 0.5, "open": true},
// 		{"id":14, "text":"包铁皮", "start_date":"02-04-2018", "duration":"6", "parent":"11", "progress": 0.8, "open": true},
// 		{"id":15, "text":"喷框外协", "start_date":"", "duration":"", "parent":"11", "progress": 0.2, "open": true},
// 		{"id":16, "text":"包板", "start_date":"02-04-2018", "duration":"7", "parent":"11", "progress": 0, "open": true},
// 		{"id":17, "text":"1号机台", "start_date":"03-04-2018", "duration":"2", "parent":"13", "progress": 1, "open": true},
// 		{"id":18, "text":"2号机台", "start_date":"06-04-2018", "duration":"3", "parent":"13", "progress": 0.8, "open": true},
// 		{"id":19, "text":"3号机台", "start_date":"10-04-2018", "duration":"4", "parent":"13", "progress": 0.2, "open": true},
// 		{"id":20, "text":"4号机台", "start_date":"10-04-2018", "duration":"4", "parent":"13", "progress": 0, "open": true},
// 		{"id":21, "text":"1号机台", "start_date":"03-04-2018", "duration":"4", "parent":"15", "progress": 0.5, "open": true},
// 		{"id":22, "text":"2号机台", "start_date":"03-04-2018", "duration":"4", "parent":"15", "progress": 0.1, "open": true},
// 		{"id":23, "text":"3号机台", "start_date":"03-04-2018", "duration":"5", "parent":"15", "progress": 0, "open": true}
// 	],
//
// };