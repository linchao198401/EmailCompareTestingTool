function moveFromOneSelectToAnotherSelect(from, to) {
	from.dblclick(function() {
		var selected = $(this).find('option:selected').remove();
		to.append(selected);
	});
	to.dblclick(function() {
		var selected = $(this).find('option:selected').remove();
		from.append(selected);
	});
}

function replaceEmailWithTextAreaTag(email) {
	var regex = /<br [\/]?>/gi;
	email = email.replace(regex, "")
	
	return email;
}

function replaceEmailWithHTMLTag(email) {
	var regex = /</gi;
	email = email.replace(regex, "&lt;");
	
	var regex = />/gi;
	email = email.replace(regex, "&gt;");

	var regex = /\n/gi;
	email = email.replace(regex, "<br />\n");
	
	return email;
}

function setDatePicker(dateInputField, position, moreAction) {
	var positionOption = position || "bottom";
	var more = moreAction || function() {
	};
	dateInputField.DatePicker({
		format : 'Y-m-d',
		date : dateInputField.val(),
		current : dateInputField.val(),
		starts : 1,
		position : positionOption,
		onChange : function(formated, dates) {
			dateInputField.val(formated);
			dateInputField.DatePickerHide();
			more();
		}
	});
}

$(function() {
	$(".btn-delete").click(function() {
		var r = confirm("Are you sure want to delete it?");
		if (r == false) {
			return false;
		}
	});
	$("textarea.lines").each(function(){
		var textarea = $(this);
		var text = textarea.val();
		var regex = /<br [\/]?>/gi;
		textarea.val(text.replace(regex, "\n"));
		
		textarea.siblings(".btn-success").click(function() {
			var regex = /\n/gi;
			textarea.val(textarea.val().replace(regex, "<br />"));
		})
	})
	
	// test case/suite execute result highlight
	$(".runStatus").each(function() {
		var status = $(this).text();
		if (status == "true") {
			$(this).parent("td").addClass("statusSuccess");
		} else {
			$(this).parent("td").addClass("statusFail");
		}
	})
})