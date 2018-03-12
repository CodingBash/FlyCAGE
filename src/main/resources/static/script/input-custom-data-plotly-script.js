if (!expressionStageOptions) {
	console.error("ERROR: expressionStageOptions must be defined before importing this script");
} else {
	// TODO: Using expressionStageOptions from selection form script. Organize
	// better

    /*
     * TODO: MODULARIZE THIS JAVASCRIPT. Same as #attachPlotGraphic on output
     * page
     */
	var plotDivId = "custom-data-plot";

	// EEG MAGNITUDE
	var correlationData = [{
		name: "INPUT GENE",
		y: [],
		mode: 'lines+markers',
		text: [],
		marker: {
			color: 'blue',
			size: 8
		},
		line: {
			width: 4
		},
		hoverinfo: 'none'
	},
	{
		name: 'INPUT GENE',
		type: 'bar',
		width: 0.1
	}];
	Plotly.plot(plotDivId, correlationData, {
		showLegend: false,
		title: 'Custom Expression Data',
		xaxis: {
			title: "Expression Stage"
		},
		yaxis: {
			title: "Expression Score",
			range: [0, 100],
			autorange: true
		},
		width: $('#plot-container').innerWidth() * .80
	});

	var d3 = Plotly.d3;
	var gd1 = d3.select('div[id="' + plotDivId + '"]');

	// TODO: Expected conflict when resizing plots with different purposes
	function plotsResize() {
		// Getting the width of .content
		/*
		 * TODO: Avoid getting innerWidth of panel. For some reason, the
		 * gene-accordion show.bs.collapse calls event before #plot-container is
		 * resized. Figure out a way to do after
		 */


		var newPlotWidth = $(window).width() < 992 ? $('#heading-custom-gene-form').innerWidth() - 20 : $('#heading-custom-gene-form').innerWidth() / 2 - 20;


		$(".js-plotly-plot").css({
			"width": newPlotWidth + "px",
		});
		$(".plotly").css({
			"width": newPlotWidth + "px",
		});

		// Applying width and height to a new variable for Plotly
		var update = {
			width: newPlotWidth,
		};
		// Using Plotly's relayout-function with graph-name and
		// the variable with the new height and width
		Plotly.relayout(gd1.node(), update);
	}

	$(window).resize(function () {
		plotsResize();
	});

	plotsResize();

	function updatePlotly(groupId, expressionStageId) {
		// Get jQuery element objects
		var checkBox = $("#" + groupId + "-" + expressionStageId + "-custom-data-sub-checkbox");
		var inputField = $("#" + groupId + "-" + expressionStageId + "-custom-input");

		// TODO: Not sure why I need ["0"] to access checked, but it works
		var checked = checkBox["0"].checked;
		var name = checkBox.attr("data-stage");

		// TODO: Find way to increase performance instead of linear search
		var findIndex = function (title) {
			for (var i = 0; i < allExpressionStages.length; i++) {
				// TODO: If title is changed to id, this condition will always
				// fail,
				// and never update plot
				if (allExpressionStages[i].expressionStageTitle == title) {
					return allExpressionStages[i].expressionStageNumericalId;
				}
			}
			return -1;
		};

		var expressionTitle = name.substring(name.indexOf('-') + 1);
		var allExpressionStages = expressionStageOptions.expressionStageList;
		if (checked == true) {

			var value = inputField.val();
			// Find index of selected stage
			var index = findIndex(expressionTitle);

			if (index != -1) {
				var plotDivId = "custom-data-plot";
				var plotDiv = document.getElementById(plotDivId);
				var plotData = plotDiv.data;
				var expressionData = plotData[0].y;
				var expressionTexts = plotData[0].text;
				var newExpressionData = expressionData.slice(0); // clone
				var newExpressionTexts = expressionTexts.slice(0); // clone

				/*
				 * Improve expression insertion algorithm
				 */
				var insertionFlag = false;
				for (var i = 0; i < expressionTexts.length && insertionFlag != true; i++) {
					// TODO: Handle equal cases if need be
					var currentIndex = findIndex(expressionTexts[i]);
					if (currentIndex == index) {
						// If stage is already in plot -> update value
						newExpressionData[i] = value;
						insertionFlag = true;
					} else if (findIndex(expressionTexts[i]) > index) {
						newExpressionData.splice(i, 0, value);
						newExpressionTexts.splice(i, 0, expressionTitle);
						insertionFlag = true;
					}
				}
				if (insertionFlag == false) {
					newExpressionData.push(value);
					newExpressionTexts.push(expressionTitle);
				}

				var update = {
					y: [newExpressionData],
					text: [newExpressionTexts]
				};
				Plotly.restyle(plotDiv, update);
			}
		} else {
			// Remove from plot

			// TODO: Modularize below (in both conditions)
			// Find index of selected stage
			var index = findIndex(expressionTitle);

			if (index != -1) {
				var plotDivId = "custom-data-plot";
				var plotDiv = document.getElementById(plotDivId);
				var plotData = plotDiv.data;
				var expressionData = plotData[0].y;
				var expressionTexts = plotData[0].text;
				var newExpressionData = expressionData.slice(0); // clone
				var newExpressionTexts = expressionTexts.slice(0); // clone

				/*
				* Improve expression removal algorithm
				*/
				for (var i = 0; i < expressionTexts.length; i++) {
					if (findIndex(expressionTexts[i]) == index) {
						newExpressionData.splice(i, 1);
						newExpressionTexts.splice(i, 1);
						break;
					}
				}
				var update = {
					y: [newExpressionData],
					text: [newExpressionTexts]
				};
				Plotly.restyle(plotDiv, update);
			}
		}
	}
	// Update plotly when checkbox updates
	$("input[id$='-custom-data-sub-checkbox']").on("change", function () {
		var combinedId = this.id.substring(0, this.id.indexOf("-custom-data-sub-checkbox"));
		var groupId = combinedId.substring(0, combinedId.indexOf("-"));
		var expressionStageId = combinedId.substring(combinedId.indexOf("-") + 1);
		updatePlotly(groupId, expressionStageId);
	});
}
