$(function() {
    $('[data-toggle="tooltip"]').tooltip()
})

var t = $('#correlation-results').DataTable({
    "columnDefs" : [ {
	"targets" : 0,
	"data" : null,
	"orderable" : false,
	"className" : 'details-control',
	"defaultContent" : ''
    }, {
	responsivePriority : 1,
	targets : 0
    }, {
	responsivePriority : 2,
	targets : 2
    }, {
	responsivePriority : 3,
	targets : 5
    }, {
	responsivePriority : 4,
	targets : 6
    }, {
	responsivePriority : 5,
	targets : 7
    }, {
	responsivePriority : 6,
	targets : 4
    }, {
	targets : 4,
	render : function(data, type, row) {
	    return (data.length > 25) ? data.substr(0, 25) + "..." : data;
	}
    } ],
    aaSorting : [ [ 1, 'asc' ] ],
    "pageLength" : 25,
    dom : 'Bfrtip',
    buttons : [ 'copy', 'csv', 'excel', 'pdf' ],
    "responsive" : {
	"details" : false
    }
});

var t2 = $('#goi-results').DataTable({
    "columnDefs" : [ {
	"targets" : 0,
	"data" : null,
	"orderable" : false,
	"className" : 'details-control',
	"defaultContent" : ''
    }, {
	responsivePriority : 1,
	targets : 0
    }, {
	responsivePriority : 2,
	targets : 2
    }, {
	responsivePriority : 3,
	targets : 5
    }, {
	responsivePriority : 4,
	targets : 6
    }, {
	responsivePriority : 5,
	targets : 7
    }, {
	responsivePriority : 6,
	targets : 4
    }, {
	targets : 4,
	render : function(data, type, row) {
	    return (data.length > 25) ? data.substr(0, 25) + "..." : data;
	}
    } ],
    aaSorting : [ [ 1, 'asc' ] ],
    "pageLength" : 25,
    dom : 'Bfrtip',
    buttons : [ 'copy', 'csv', 'excel', 'pdf' ],
    "responsive" : {
	"details" : false
    }
});

/*
 * TODO: Somehow move the HTML code to Thymeleaf tempalte? Very messy to attach
 * too much HTML from javascript
 * 
 */
/*
 * Formatting function for row details - modify as you need
 */
function attachPlotHtml(pairwiseCorrelationDataAjaxRequestBody) {
    // TODO: Modularize these two variables
    var plotDivId = pairwiseCorrelationDataAjaxRequestBody.inputGene + '-' + pairwiseCorrelationDataAjaxRequestBody.targetGene + '-graph';
    var tableIdExtender = pairwiseCorrelationDataAjaxRequestBody.inputGene + '-' + pairwiseCorrelationDataAjaxRequestBody.targetGene;

    var expressionStageMapping = '<button type="button" class="btn btn-primary expression-btn" data-toggle="modal" data-target="#exampleModalLong">' + 'See Stage Mapping' + '</button>';

    /*
     * HTML for the correlation-graph normalize button. Will be attached to the
     * result string
     */
    var normalizationButtonHtml = '<button type="button" class="btn btn-primary normalization-btn text-center">Normalize</button>';
    /*
     * TODO: Add Datatable - do this after adding sorting to the plotly TODO:
     * May want to do this after the selective expression stage too (since I
     * will need logic to make the table dynamic instead of static)
     */
    return '<div id="' + plotDivId + '" class ="custom-plotly-plot"></div>' + '<div class="text-center">' + normalizationButtonHtml + expressionStageMapping + '</div>';
}

/*
 * Normalization Container: Contains list of plots that has been normalized at
 * least once List of objects: { plotDivId: string isNormalized: boolean,
 * originalData: 2D list }
 */
var normalizationContainer = []
// TODO: When closing the tab, normalization disappears
// and is unable to normalize again since the object is
// still in the container.
// Either find a way to not reload the data (preferred,
// but longer), or remove the object when closing
// Look into more exception scenarios
/*
 * On-click event for the normalize button
 */
$(document).on("click", '.normalization-btn', function() {
    normalizePlotData($(event.target).parents().children(".custom-plotly-plot")[0].id, event.target); // TODO:
    // Find
    // a
    // better
    // way
    // to
    // get
    // the
    // plot
    // (this
    // seems
    // inefficient)
});

/*
 * Function to normalize the plot data
 */
function normalizePlotData(plotDivId, eventTarget) {
    // Retrieves all normalizes plots with the same
    // plotDivId
    var normalizedPlotResults = $.grep(normalizationContainer, function(e) {
	return e.plotDivId == plotDivId;
    });

    // Retrieves the plot object using its ID
    var plotDiv = document.getElementById(plotDivId);

    // If there is no entry in the
    // normalizationContainer (hasn't been
    // normalized yet) or there is an entry but is
    // not normalized -> let's normalize
    if (normalizedPlotResults.length == 0 || normalizedPlotResults[0].isNormalized == false) {
	/*
	 * Retreive appropriate data
	 */
	var plotData = plotDiv.data;
	var inputGeneData = plotData[1].y;
	var targetGeneData = plotData[0].y;
	var newInputGeneData = [];
	var newTargetGeneData = [];

	/*
	 * Retreive max/min of both sets (used for normalization)
	 */
	var inputMax = Math.max.apply(null, inputGeneData);
	var inputMin = Math.min.apply(null, inputGeneData);
	var targetMax = Math.max.apply(null, targetGeneData);
	var targetMin = Math.min.apply(null, targetGeneData);

	// Create normalization function
	var normalizer = function(val, max, min) {
	    return (val - min) / (max - min);
	}

	/*
	 * Normalize input gene data
	 */
	for (var i = 0; i < inputGeneData.length; i++) {
	    newInputGeneData.push(normalizer(inputGeneData[i], inputMax, inputMin));
	}

	/*
	 * Normalize target gene data
	 */
	for (var i = 0; i < targetGeneData.length; i++) {
	    newTargetGeneData.push(normalizer(targetGeneData[i], targetMax, targetMin));
	}

	/*
	 * Update the plot with the normalized data
	 */
	var update = {
	    y : [ newInputGeneData, newTargetGeneData ],
	    title : "Correlation Line Graph (Normalized)" // TODO:
	// Title
	// change
	// not
	// working
	};
	Plotly.restyle(plotDiv, update, [ 1, 0 ])

	// Change button text to 'Original'
	$(eventTarget).html('Original');

	/*
	 * Add/update the entry in the normalizedContainer
	 */
	if (normalizedPlotResults.length == 0) {
	    normalizationContainer.push({
		plotDivId : plotDivId,
		isNormalized : true,
		originalData : [ inputGeneData, targetGeneData ]
	    });
	} else {
	    normalizedPlotResults[0].isNormalized = true;
	}
    } else { // Else: The plot is normalized

	/*
	 * Update the plot with the original data
	 */
	var update = {
	    y : [ normalizedPlotResults[0].originalData[0], normalizedPlotResults[0].originalData[1] ],
	    title : 'Correlation Line Graph (Original)' // TODO:
	// Title
	// change
	// not
	// working
	};
	Plotly.restyle(plotDiv, update, [ 1, 0 ])

	// Update the entry in the normalized
	// container
	normalizedPlotResults[0].isNormalized = false; // No
	// need
	// to
	// check
	// if
	// length
	// !=
	// 0,
	// to
	// be
	// in
	// this
	// block,
	// it
	// has
	// to
	// be

	// Change button text to 'Normalize'
	$(eventTarget).html('Normalize');
    }
}

function retrieveCorrelationData(pairwiseCorrelationDataAjaxRequestBody) {
    var pairwiseGeneCorrelationData = null;

    $.ajax({
	type : "POST",
	contentType : "application/json",
	url : "/pairwise-correlation-data",
	data : JSON.stringify(pairwiseCorrelationDataAjaxRequestBody),
	dataType : 'json',
	cache : false,
	timeout : 600000,
	success : function(data) {
	    console.log(data);
	    pairwiseGeneCorrelationData = data;
	    attachPlotGraphic(pairwiseGeneCorrelationData, pairwiseCorrelationDataAjaxRequestBody)
	},
	error : function(e) {
	    console.log(e);
	    resultHtml = "<h4>Plot load failed</h4>"
	}
    });

    // Possibly of being null due to async
    return pairwiseGeneCorrelationData;
}

function attachPlotGraphic(pairwiseGeneCorrelationData, pairwiseCorrelationDataAjaxRequestBody) {
    var plotDivId = pairwiseCorrelationDataAjaxRequestBody.inputGene + '-' + pairwiseCorrelationDataAjaxRequestBody.targetGene + '-graph';

    // EEG MAGNITUDE
    var correlationData = [ {
	name : pairwiseCorrelationDataAjaxRequestBody.inputGene,
	y : pairwiseGeneCorrelationData.inputGeneData,
	mode : 'lines+markers',
	text : selectedExpressionStageLabels,
	marker : {
	    color : 'blue',
	    size : 8
	},
	line : {
	    width : 4
	}
    }, {
	name : pairwiseCorrelationDataAjaxRequestBody.targetGene,
	y : pairwiseGeneCorrelationData.targetGeneData,
	text : selectedExpressionStageLabels,
	mode : 'lines+markers',
	marker : {
	    color : 'red',
	    size : 8
	},
	line : {
	    width : 4,
	    dash : "dot"
	}
    } ];
    Plotly.plot(plotDivId, correlationData, {
	title : 'Correlation Line Graph (Original)',
	xaxis : {
	    title : "Expression Stage"
	// range : [0, 104]
	},
	yaxis : {
	    title : "Expression Score",
	},
	width : $('#result-panel').innerWidth() * .80
    });

    var d3 = Plotly.d3;
    var gd1 = d3.select('div[id="' + plotDivId + '"]');

    function plotsResize() {
	// Getting the width of .content
	var newTableWidth = $('#result-panel').innerWidth() * .95;
	var newPlotWidth = $('#result-panel').innerWidth() * .85;

	// Apply width and height to two divs that are
	// created by
	// Plotly. Fixed some issueswith hidden overfull
	// elements.
	$("table[id$=-results]").css({
	    "width" : newTableWidth + "px",
	});

	$(".js-plotly-plot").css({
	    "width" : newPlotWidth + "px",
	});
	$(".plotly").css({
	    "width" : newPlotWidth + "px",
	});

	// Applying width and height to a new variable for
	// Plotly
	var update = {
	    width : newPlotWidth,
	};
	// Using Plotly's relayout-function with graph-name
	// and
	// the variable with the new height and width
	Plotly.relayout(gd1.node(), update);
    }

    $(window).resize(function() {
	plotsResize();
    });

    plotsResize();
}

// Add event listener for opening and closing
// details
$('[id$="-results"] tbody').on('click', 'td.details-control', function() {
    var tr = $(this).closest('tr');

    /*
     * TODO: Adhoc/hard-coded way of determining which table this event belongs
     * to. Make more dynamic
     */
    var row;
    if ($(tr).closest('table').attr('id') == "correlation-results") {
	row = t.row(tr);
    } else {
	row = t2.row(tr);
    }

    if (row.child.isShown()) {
	// This row is already open - close it
	row.child.hide();
	tr.removeClass('shown');
    } else {

	var pairwiseCorrelationDataAjaxRequestBody = {
	    inputGene : finalResponseCorrelationResult.inputGene.dbIdentifier,
	    targetGene : row.data()[2],
	    selectedExpressionStages : expressionStages
	};

	// Open this row
	row.child(attachPlotHtml(pairwiseCorrelationDataAjaxRequestBody)).show();
	var pairwiseGeneCorrelationData = retrieveCorrelationData(pairwiseCorrelationDataAjaxRequestBody);
	tr.addClass('shown');
    }
});
/*
 * TODO: Aren't these lists already sorted?
 */
finalResponseCorrelationResult.correlationResults.sort(function(a, b) {
    if (a.corrResult.rVal < b.corrResult.rVal) {
	return 1;
    } else if (a.corrResult.rVal > b.corrResult.rVal) {
	return -1;
    } else {
	return 0;
    }
});

finalResponseCorrelationResult.correlationResultsForGenesOfInterest.sort(function(a, b) {
    if (a.corrResult.rVal < b.corrResult.rVal) {
	return 1;
    } else if (a.corrResult.rVal > b.corrResult.rVal) {
	return -1;
    } else {
	return 0;
    }
});

var rank = 1;
finalResponseCorrelationResult.correlationResults.forEach(function(correlationResult) {

    var buttonHtml = "";
    dataToggle = 'datatoggle="tooltip" data-placement="top" title="FlyMine ' + correlationResult.gene.dbIdentifier + ' Report"';
    buttonHtml += '<button type="button" class="btn btn-primary flymine-button" value="' + correlationResult.gene.dbIdentifier + '"' + dataToggle + '>FlyMine</button>';
    // Append any other buttons

    t.row.add(
	    [ null, correlationResult.rank, correlationResult.gene.dbIdentifier, correlationResult.gene.secondaryIdentifier, correlationResult.gene.geneName,
		    correlationResult.corrResult.rValueString, correlationResult.corrResult.pValSigFigs, buttonHtml ]).draw(false);
    rank++;
});
// Now that buttons are added, activate tooltip for all
// of them
$('.flymine-button').tooltip();

$('.flymine-button').on('click', function() {
    var geneDbId = $(this).val();
    var flymineUrl = 'http://www.flymine.org/query/portal.do?externalid=' + geneDbId + '&class=Gene&origin=fly-tran-webapp&organism=D.%20melanogaster'
    window.open(flymineUrl);
});

rank = 1;
finalResponseCorrelationResult.correlationResultsForGenesOfInterest.forEach(function(correlationResult) {

    var buttonHtml = "";
    dataToggle = 'datatoggle="tooltip" data-placement="top" title="FlyMine ' + correlationResult.gene.dbIdentifier + ' Report"';
    buttonHtml += '<button type="button" class="btn btn-primary flymine-button" value="' + correlationResult.gene.dbIdentifier + '"' + dataToggle + '>FlyMine</button>';
    // Append any other buttons

    t2.row.add(
	    [ null, correlationResult.rank, correlationResult.gene.dbIdentifier, correlationResult.gene.secondaryIdentifier, correlationResult.gene.geneName,
		    correlationResult.corrResult.rValueString, correlationResult.corrResult.pValSigFigs, buttonHtml ]).draw(false);
    rank++;
});
// Now that buttons are added, activate tooltip for all
// of them
$('.flymine-button').tooltip();

$('.flymine-button').on('click', function() {
    var geneDbId = $(this).val();
    var flymineUrl = 'http://www.flymine.org/query/portal.do?externalid=' + geneDbId + '&class=Gene&origin=fly-tran-webapp&organism=D.%20melanogaster'
    window.open(flymineUrl);
});