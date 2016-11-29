function paintCell(cell) {
	var cellValue = cell.getAttribute("value");
	if(cellValue >= 500) {
		cell.style.backgroundColor = "red";
	} else if(cellValue >= 250 && cellValue < 500) {
		cell.style.backgroundColor = "yellow";
	} else if(cellValue == 0) {
		cell.style.backgroundColor = "#00ff00";
	}
}

var severeCell = document.getElementsByClassName("severeHits");
for(i = 0; i < severeCell.length; i++) {
	paintCell(severeCell[i]);
}

