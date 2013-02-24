var tableBody;

function maakRij(x, y, type, rij) {
	var tr = document.createElement('tr');
	if (type === '+') {
		tr.className = 'add';
	} else if (type === '-') {
		tr.className = 'del';
	}
	var td1 = document.createElement('td');
	var td2 = document.createElement('td');
	var td3 = document.createElement('td');
	td1.className = 'codekolom';
	td2.className = 'codekolom';
	td3.className = 'bredecode';
	var txt1 = document.createTextNode(y);
	var txt2 = document.createTextNode(x);
	var txt3 = document.createTextNode(type + ' ' + rij);
	td1.appendChild(txt1);
	td2.appendChild(txt2);
	td3.appendChild(txt3);
	tr.appendChild(td1);
	tr.appendChild(td2);
	tr.appendChild(td3);
	tableBody.appendChild(tr);
}

function getDiff(matrix, a1, a2, x, y) {
	if (x > 0 && y > 0 && a1[y - 1] === a2[x - 1]) {
		getDiff(matrix, a1, a2, x - 1, y - 1);
		maakRij(x, y, ' ', a1[y - 1]);
	} else {
		if (x > 0 && (y === 0 || matrix[y][x - 1] >= matrix[y - 1][x])) {
			getDiff(matrix, a1, a2, x - 1, y);
			maakRij(x, '', '+', a2[x - 1]);
		} else if (y > 0 && (x === 0 || matrix[y][x - 1] < matrix[y - 1][x])) {
			getDiff(matrix, a1, a2, x, y - 1);
			maakRij('', y, '-', a1[y - 1], '');
		} else {
			return;
		}
	}
}

function getDiffByLine(matrix, a1, a2, line) {
	if (a1[line] === a2[line]) {
		maakRij(line, line, ' ', a1[line]);
	} else {
		maakRij('', line, '-', a1[line], '');
		maakRij(line, '', '+', a2[line]);
	}
}

function diff(a1, a2) {
	var matrix = new Array(a1.length + 1);
	for ( var y = 0; y < matrix.length; y++) {
		matrix[y] = new Array(a2.length + 1);
		for ( var x = 0; x < matrix[y].length; x++) {
			matrix[y][x] = 0;
		}
	}
	for ( var y = 1; y < matrix.length; y++) {
		for ( var x = 1; x < matrix[y].length; x++) {
			if (a1[y - 1] === a2[x - 1]) {
				matrix[y][x] = 1 + matrix[y - 1][x - 1];
			} else {
				matrix[y][x] = Math.max(matrix[y - 1][x], matrix[y][x - 1]);
			}
		}
	}

	try {
//		var maxLine = a1.length;
//		for ( var line = 1; line < maxLine; line++) {
//			getDiffByLine(matrix, a1, a2, line);
//		}

		getDiff(matrix, a1, a2, x - 1, y - 1);
	} catch (e) {
		alert(e);
	}
}

function clearTableBody() {
	while (tableBody.hasChildNodes()) {
		tableBody.removeChild(tableBody.lastChild);
	}
}

$(function() {
	var een = document.getElementById('een');
	var twee = document.getElementById('twee');
	tableBody = document.getElementById('res');

	var a1= een.value.split('\n');
	var a2= twee.value.split('\n');
	var matrix = new Array(a1.length + 1);
	for ( var y = 0; y < matrix.length; y++) {
		matrix[y] = new Array(a2.length + 1);
		for ( var x = 0; x < matrix[y].length; x++) {
			matrix[y][x] = 0;
		}
	}
	for ( var y = 1; y < matrix.length; y++) {
		for ( var x = 1; x < matrix[y].length; x++) {
			if (a1[y - 1] === a2[x - 1]) {
				matrix[y][x] = 1 + matrix[y - 1][x - 1];
			} else {
				matrix[y][x] = Math.max(matrix[y - 1][x], matrix[y][x - 1]);
			}
		}
	}
	
	function compareBySection() {
		clearTableBody();
		getDiff(matrix, a1, a2, x - 1, y - 1);
	}
	
	function compareByLine() {
		clearTableBody();
		var maxLine = a1.length;
		for ( var line = 1; line < maxLine; line++) {
			getDiffByLine(matrix, a1, a2, line);
		}
	}
	
	// default would be compare by section
	compareBySection();
	
	$("#compareBySectionButton").click(function(){
		compareBySection();
		$("#compareBySectionButton").toggleClass("btn-info");
		$("#compareByLineButton").toggleClass("btn-info");
	})
	$("#compareByLineButton").click(function(){
		compareByLine();
		$("#compareBySectionButton").toggleClass("btn-info");
		$("#compareByLineButton").toggleClass("btn-info");
	})
})