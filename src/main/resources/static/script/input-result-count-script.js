if (!geneResultCount) {
    console.error("ERROR: geneResultCount must be defined before importing this script");
} else {
    $(function() {
	var handle = $("#custom-handle");

	var createEvent = function(sliderElement) {
	    /*
	     * If input = default (meaning the form is fresh) Do the initial
	     * createSlider event
	     */
	    $("#slider").slider("option", "value", geneResultCount); // Value
									// set
	    // to
	    // default
	    // TODO:
	    // must be
	    // in sync
	    // with
	    // input box
	    handle.text(geneResultCount); // Set default value to handle
	};

	var slideEvent = function(value) {
	    handle.text(value);

	    var value = $("#slider").slider("option", "value");
	    $('#result-count').val(value);
	};

	var changeEvent = function() {
	    var value = $("#slider").slider("option", "value");
	    $('#result-count').val(value);
	};

	$("#slider").slider({
	    orientation : "horizontal",
	    range : "min",
	    create : function() {
		createEvent(this)
	    },
	    slide : function(event, ui) {
		slideEvent(ui.value);
	    },
	    change : function(event, ui) {
		changeEvent();
	    }
	});
	$("#slider").slider("option", "min", 2); // Min for correlation to
						    // work
	$("#slider").slider("option", "max", 500); // Max genes recommended
						    // (may be
	// overrided by input number
	// box)

	$('#result-count').on('change', function() {
	    var val = $('#result-count').val();
	    $("#slider").slider("option", "value", val);
	    slideEvent(val);
	});
    });
}