function paintCell(cell) {
	var cellValue = cell.getAttribute("value");
	if(cellValue >= 500) {
		cell.style.backgroundColor = "#ff0000";
	} else if(cellValue >= 250 && cellValue < 500) {
		cell.style.backgroundColor = "#999999";
	} else if(cellValue == 0) {
		cell.style.backgroundColor = "#00ff00";
	}
}

var severeHits = document.getElementsByClassName("severeHits");
var severeType = document.getElementsByClassName("severeType");
for(i = 0; i < severeHits.length; i++) {
	paintCell(severeHits[i]);
}
for(i = 0; i < severeType.length; i++) {
	paintCell(severeType[i]);
}
