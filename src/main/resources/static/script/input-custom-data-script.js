if (!expressionStageOptions) {
	console.error("ERROR: expressionStageOptions must be defined before importing this script");
} else {

	const DEFAULT_CUSTOM_DATA = 0;

	expressionStageOptions.expressionStageGroupList.forEach(function (expressionStageGroup) {
		expressionStageGroup.expressionStageList.forEach(function (expressionStage) {
			$(function () {
				var sliderId = "#" + expressionStageGroup.groupId + '-' + expressionStage.expressionStageId + "-slider"
				var sliderObject = $(sliderId);
				var handleObject = $("#" + expressionStageGroup.groupId + '-' + expressionStage.expressionStageId + "-custom-handle");
				var inputObject = $("#" + expressionStageGroup.groupId + '-' + expressionStage.expressionStageId + "-custom-input");

				var createEvent = function (sliderElement) {
					/*
					 * If input = default (meaning the form is fresh) Do the
					 * initial createSlider event
					 */
					sliderObject.slider("option", "value", DEFAULT_CUSTOM_DATA); // Value
					// set
					// to
					// default
					// TODO:
					// must
					// be
					// in
					// sync
					// with
					// input
					// box
					handleObject.text(DEFAULT_CUSTOM_DATA); // Set default value
					// to
					// handle
				};

				var slideEvent = function (value) {
					handleObject.text(value);
					inputObject.val(value);

					// Change identical sliders
					$("div[id$='" + '-' + expressionStage.expressionStageId + "-slider']").not(sliderId).slider("option", "value", value);
				};

				// TODO: this event is called on pageLoad for all sliders, thus
				// reducing performance. Optimize
				var changeEvent = function (value) {
					inputObject.val(value);
					handleObject.text(value);
				};

				// TODO: Move more logic to this event
				var stopEvent = function (value) {
					// Update Plotlyjs. Commenting out since checkbox event
					// fixed
					// updatePlotly(expressionStageGroup.groupId,
					// expressionStage.expressionStageId);
					// TODO: Modularize this
					// Condition prevents change on pageload. Maybe wrap around
					// all
					// method calls
					if (value != 0) {
						// Change identical subcheckboxes
						programmaticallyChangeSubCheckbox(sliderObject.parent().parent().find("input[type='checkbox']:checkbox"), "-custom-data-", true);

						// TODO: Modularize this logic (although they are
						// slightly
						// different
						var oneChecked = false;
						var allTrue = true;
						$("input[id$='-custom-data-sub-checkbox']").each(function (index, currentElement) {
							if (currentElement.checked == true) {
								oneChecked = true;
							} else if (currentElement.checked == false) {
								allTrue = false;
							}
						});
						$("#insert-gene").prop("required", !oneChecked);
					}

				}

				sliderObject.slider({
					orientation: "horizontal",
					range: "min",
					create: function () {
						createEvent(this)
					},
					slide: function (event, ui) {
						slideEvent(ui.value);
					},
					change: function (event, ui) {
						changeEvent(ui.value);
					},
					stop: function (event, ui) {
						stopEvent(ui.value);
					}
				});
				sliderObject.slider("option", "min", 0);
				sliderObject.slider("option", "max", 100);
				// MAKE SURE STEP IS SAME AS NUMBER INPUT ELEMENT
				sliderObject.slider("option", "step", 1);

				inputObject.on('change', function () {
					var val = inputObject.val();
					
					/*
					 * Range enforcer
					 */
					var max = parseInt(inputObject.prop('max'));
					var min = parseInt(inputObject.prop('min'));
					if (inputObject.val() > max) {
						inputObject.val(max);
					}
					else if (inputObject.val() < min) {
						inputObject.val(min);
					}
					val = inputObject.val();

					sliderObject.slider("option", "value", val);
					slideEvent(val);

					// Programmatically change subcheckbox if value is greater than 0.
					// TODO: THIS NEEDS TO BE MODULARIZED, it is identical to the code in the slider change event above
					if (val != 0) {
						// Change identical subcheckboxes
						programmaticallyChangeSubCheckbox(inputObject.parent().parent().find("input[type='checkbox']:checkbox"), "-custom-data-", true);

						// TODO: Modularize this logic (although they are
						// slightly
						// different
						var oneChecked = false;
						var allTrue = true;
						$("input[id$='-custom-data-sub-checkbox']").each(function (index, currentElement) {
							if (currentElement.checked == true) {
								oneChecked = true;
							} else if (currentElement.checked == false) {
								allTrue = false;
							}
						});
						$("#insert-gene").prop("required", !oneChecked);
					}

				});
			});
		});
	});

	// If custom-data form being used, remove target gene form requirement
	$("input[id$='-custom-data-sub-checkbox'], input[id$='-custom-data-main-checkbox']").on("change", function () {
		// TODO: Modularize this logic (although they are slightly
		// different
		// REMOVE TARGET GENE FORM REQUIREMENT
		var oneChecked = false;
		var allTrue = true;
		$("input[id$='-custom-data-sub-checkbox']").each(function (index, currentElement) {
			if (currentElement.checked == true) {
				oneChecked = true;
			} else if (currentElement.checked == false) {
				allTrue = false;
			}
		});
		$("#insert-gene").prop("required", !oneChecked);
	});

	/*
	 * Custom Data Checkbox custom logic TODO: Determine if I need this here, or
	 * if I need the trigger
	 */
	$("input[type='checkbox'][class*='custom-data']:checkbox").prop('checked', false).trigger("change");
}
