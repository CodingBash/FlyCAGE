/**
 * 
 */

/*
 * Adjust the main-checkbox relative to the input sub-checkbox i.e. if some but
 * not all "group"-sub-checkbox is selected, make "group"-master-checkbox
 * *indeterminate*
 */
var adjustMasterCheckboxes = function(subCheckbox, form_group) {
	/*
	 * Retrieve the target class name (i.e. embryogenesis-sub-checkbox
	 */
	allClassNames = $(subCheckbox).prop('class').split(" ");
	checkboxClassName = "";
	$.each(allClassNames, function(i) {
		if (allClassNames[i].includes(form_group + "sub-checkbox")) {
			checkboxClassName = allClassNames[i];
			return false; // break
		}
	});

	/*
	 * Get the expression stage group name
	 */
	groupName = checkboxClassName.substring(0, checkboxClassName
			.indexOf(form_group + 'sub-checkbox'));

	var oneChecked = false;
	var allTrue = true;
	$('.' + groupName + form_group + 'sub-checkbox').each(function(index, currentElement) {
		if (currentElement.checked == true) {
			oneChecked = true;
		} else if (currentElement.checked == false) {
			allTrue = false;
		}
	});
	if (oneChecked && !allTrue) {
		$('.' + groupName + form_group + 'main-checkbox').prop('indeterminate', true);
	} else if (allTrue) {
		$('.' + groupName + form_group + 'main-checkbox').prop('indeterminate', false);
		$('.' + groupName + form_group + 'main-checkbox').prop('checked', true);
	} else if (!oneChecked) {
		$('.' + groupName + form_group + 'main-checkbox').prop('indeterminate', false);
		$('.' + groupName + form_group + 'main-checkbox').prop('checked', false);
	} else {
		$('.' + groupName + form_group + 'main-checkbox').prop('indeterminate', false);
	}
}

/*
 * Change all identical checkboxes relative to the input sub-checkbox
 */
var changeIdenticalCheckboxes = function(subCheckbox, form_group) {
	wholeId = $(subCheckbox).prop('id');
	stageId = wholeId.substring(wholeId.indexOf('-'), wholeId.length)
	identicalCheckboxes = $('[id $= ' + stageId + ']');
	identicalCheckboxes.prop('checked', subCheckbox.checked);

	identicalCheckboxes.each(function(index, currentCheckbox) {
		adjustMasterCheckboxes(currentCheckbox, form_group);
	});

}

var form_group = [ "-custom-data-", "-stage-selection-" ];
$.each(form_group, function(k) {
	$.each(groups, function(i) {
		/*
		 * Event for main-checkbox change
		 */
		$('.' + groups[i] + form_group[k] + 'main-checkbox').change(function() {
			checkboxesToChange = $('.' + groups[i] + form_group[k] + 'sub-checkbox');
			checkboxesToChange.prop("checked", this.checked);
			checkboxesToChange.each(function(index, currentCheckbox) {
				changeIdenticalCheckboxes(currentCheckbox, form_group[k]);
			});
		});

		/*
		 * Event for sub-checkbox change
		 */
		$('.' + groups[i] + form_group[k] + 'sub-checkbox').change(function() {
			adjustMasterCheckboxes(this, form_group[k]);
			changeIdenticalCheckboxes(this, form_group[k]);

		});

	});
})
