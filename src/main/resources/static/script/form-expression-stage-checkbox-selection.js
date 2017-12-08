/**
 * 
 */

	/*
	 * Adjust the main-checkbox relative to the input sub-checkbox i.e. if some
	 * but not all "group"-sub-checkbox is selected, make
	 * "group"-master-checkbox *indeterminate*
	 */
	var adjustMasterCheckboxes = function(subCheckbox) {
		/*
		 * Retrieve the target class name
		 * (i.e. embryogenesis-sub-checkbox
		 */
		allClassNames = $(subCheckbox).prop('class').split(" ");
		checkboxClassName = "";
		$.each(allClassNames, function(i) {
			if (allClassNames[i].includes("-sub-checkbox")) {
				checkboxClassName = allClassNames[i];
				return false; // break
			}
		});
		
		/*
		 * Get the expression stage group name
		 */
		groupName = checkboxClassName.substring(0, checkboxClassName
				.indexOf('-sub-checkbox'));

		var oneChecked = false;
		var allTrue = true;
		$('.' + groupName + '-sub-checkbox').each(
				function(index, currentElement) {
					if (currentElement.checked == true) {
						oneChecked = true;
					} else if (currentElement.checked == false) {
						allTrue = false;
					}
				});
		if (oneChecked && !allTrue) {
			$('.' + groupName + '-main-checkbox').prop('indeterminate', true);
		} else if (allTrue) {
			$('.' + groupName + '-main-checkbox').prop('indeterminate', false);
			$('.' + groupName + '-main-checkbox').prop('checked', true);
		} else if (!oneChecked) {
			$('.' + groupName + '-main-checkbox').prop('indeterminate', false);
			$('.' + groupName + '-main-checkbox').prop('checked', false);
		} else {
			$('.' + groupName + '-main-checkbox').prop('indeterminate', false);
		}
	}

	/*
	 * Change all identical checkboxes relative to the input sub-checkbox
	 */
	var changeIdenticalCheckboxes = function(subCheckbox) {
		wholeId = $(subCheckbox).prop('id');
		stageId = wholeId.substring(wholeId.indexOf('-'), wholeId.length)
		identicalCheckboxes = $('[id $= ' + stageId + ']');
		identicalCheckboxes.prop('checked', subCheckbox.checked);

		identicalCheckboxes.each(function(index, currentCheckbox) {
			adjustMasterCheckboxes(currentCheckbox);
		});

	}
	
$.each(groups, function(i) {
	/*
	 * Event for main-checkbox change
	 */
	$('.' + groups[i] + '-main-checkbox').change(function() {
		checkboxesToChange = $('.' + groups[i] + '-sub-checkbox');
		checkboxesToChange.prop("checked", this.checked);
		checkboxesToChange.each(function(index, currentCheckbox) {
			changeIdenticalCheckboxes(currentCheckbox);
		});
	});

	/*
	 * Event for sub-checkbox change
	 */
	$('.' + groups[i] + '-sub-checkbox').change(function() {
		adjustMasterCheckboxes(this);
		changeIdenticalCheckboxes(this);

	});

});