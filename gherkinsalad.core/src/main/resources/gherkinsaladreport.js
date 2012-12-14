$(document).ready(function(){
	$(".title").click(function(){
		$(this).next().toggle();
		$(this).toggleClass('open');
	});
	
	$(".image").colorbox();
	
	$(".warn").each(function() {
		var $node = $(this).parent().prev();
		$node.addClass("warn");
		var $parent = $node.parent().parent().prev();
		$parent.addClass("warn");
		$parent.parent().parent().prev().addClass("warn");
	});
	
	$(".action.error").each(function() {
		var $node = $(this).parent().prev();
		$node.addClass("error");
		var $parent = $node.parent().parent().prev();
		$parent.addClass("error");
		$parent.parent().parent().prev().addClass("error");
	});
	
	$(".feature").each(function() {
		var count = $(this).find(".scenario").size();
		$(this).find(".scenario-count").html(count);
	});
	
	$("#expandAll").click(function() {
		$(".content").show();
		$(".content").prev().removeClass("open");
		actions_and_info();
	});
	
	$("#collapseAll").click(function() {
		$(".content").hide();
		$(".content").prev().addClass("open");
		actions_and_info();
	});
	
	$("#failedScenarios").click(function() {
		showOrHideFailedScenarios();
		$("#no-warning-scenarios").hide();
		$('.feature').hide();
		var $node = $('.feature .title.error').parent();
		$node.show();
		$node.find('.scenario').hide();
		$node.find('.scenario .title.error').parent().show();
		actions_and_info();
	});
	
	$("#warningScenarios").click(function() {
		showOrHideWarningScenarios();
		$("#no-failed-scenarios").hide();
		$('.feature').hide();
		var $node = $('.feature .title.warn').parent();
		$node.show();
		$node.find('.scenario').hide();
		$node.find('.scenario .title.warn').parent().show();
		actions_and_info();
	});
	
	$("#showAll").click(function() {
		$("#no-warning-scenarios").hide();
		$("#no-failed-scenarios").hide();
		$('.feature').show();
		$('.scenario').show();
		actions_and_info();
	});
	
	$("#showInfo").click(function() {
		actions_and_info();
	});
	
	$("#showActions").click(function() {
		actions_and_info();
	});
});

function actions_and_info() {
	if($("#showInfo").attr('checked')) {
		$('.info').show();
	} else {
		$('.info').hide();
	}
	if($("#showActions").attr('checked')) {
		$('.action').show();
	} else {
		$('.action').hide();
	}
}

function showOrHideWarningScenarios() {
	var n = $(".warn").size();
	if(n < 1) {
		$("#no-warning-scenarios").show();
	} else {
		$("#no-warning-scenarios").hide();
	}
}

function showOrHideFailedScenarios() {
	var n = $(".error").size();
	if(n < 1) {
		$("#no-failed-scenarios").show();
	} else {
		$("#no-failed-scenarios").hide();
	}
}