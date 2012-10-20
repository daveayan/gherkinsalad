$(document).ready(function(){
	$(".title").click(function(){
		$(this).next().toggle();
	});
	
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
	
	$("#expandAll").click(function() {
		$(".content").show();
		actions_and_info();
	});
	
	$("#collapseAll").click(function() {
		$(".content").hide();
		actions_and_info();
	});
	
	$("#failedScenarios").click(function() {
		$('.feature').hide();
		var $node = $('.feature .title.error').parent();
		$node.show();
		$node.find('.scenario').hide();
		$node.find('.scenario .title.error').parent().show();
		actions_and_info();
	});
	
	$("#warningScenarios").click(function() {
		$('.feature').hide();
		var $node = $('.feature .title.warn').parent();
		$node.show();
		$node.find('.scenario').hide();
		$node.find('.scenario .title.warn').parent().show();
		actions_and_info();
	});
	
	$("#showAll").click(function() {
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
});