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
	});
	
	$("#collapseAll").click(function() {
		$(".content").hide();
	});
	
	$("#failedScenarios").click(function() {
		$('.feature').hide();
		var $node = $('.feature .title.error').parent();
		$node.show();
		$node.find('.scenario').hide();
		$node.find('.scenario .title.error').parent().show();
	});
	
	$("#warningScenarios").click(function() {
		$('.feature').hide();
		var $node = $('.feature .title.warn').parent();
		$node.show();
		$node.find('.scenario').hide();
		$node.find('.scenario .title.warn').parent().show();
	});
	
	$("#showAll").click(function() {
		$('.feature').show();
		$('.scenario').show();
	});
});