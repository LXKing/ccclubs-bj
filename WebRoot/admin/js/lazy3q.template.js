/**
Segment{
	String type;
	String content;
	Object data;	
}
**/
window["lzjt"]=function($tempText,$tempData){
	var $syntax = [];
	var $variable = "abcdefghijklmnopqrstuvwxyz";
	var $iv = 0;	
	if(window.console && !window.consoled){
		window.console("感谢使用lazy3q的前端模板引擎,更多了解请访问www.lazy3q.com");
		window.consoled = true;
	}
	while($tempText.indexOf("#[")>-1){
		var $start=$tempText.indexOf("#[");
		$syntax[$syntax.length] = $tempText.substring(0,$start).replace(/\"/g,"\\\"").replace(/\n/g,"\\n").replace(/\r/g,"\\r");
		var $end=$tempText.indexOf("]#");
		if($end==-1){
			alert($syntax[$syntax.length-1]+"  附近的表达式不成对");
			break;
		}
		var $code = $tempText.substring($start,$end+2);
		var $vCode = $tempText.substring($start+2,$end);
		if($vCode.indexOf("#[")>-1){
			alert($vCode+"  附近的表达式不成对");
			break;
		}
		$code = $code.replace("&nbsp;"," ").replace("&gt;",">").replace("&lt;","<");
		$syntax[$syntax.length] = $code;
		$tempText = $tempText.substr($end+2);
	}	
	$syntax[$syntax.length] = $tempText.replace(/\"/g,"\\\"").replace(/\n/g,"\\n").replace(/\r/g,"\\r");
	var empty=function(value){
		return (value==undefined || value==null || (""+value)=="");
	}
	var $returnHtml = "";
	var js = "{\n";
	for(var o in $tempData){
		$js+="var "+o+" = $tempData."+o+";\n";
	}
	$js+="$returnHtml += \"\";\n";
	for(var o in $syntax){
		if($syntax[o].indexOf("#[")==-1){
			$js +="$returnHtml+=\""+$syntax[o]+"\";\n";
		}else if($syntax[o]=="#[for]#" || $syntax[o]=="#[if]#"){
			$js +="}\n";
		}else if($syntax[o].indexOf("#[!")==0){
			var $code = $syntax[o].replace("#[!","").replace("]#","");
			$js+=$code+"\n";
		}else if($syntax[o].indexOf("#[if")==0){
			var $code = $syntax[o].replace("#[if ","").replace("]#","");
			$js+="if("+$code+"){\n";
		}else if($syntax[o].indexOf("#[elseif")==0){
			var $code = $syntax[o].replace("#[elseif ","").replace("]#","");
			$js+="}else if("+$code+"){\n";
		}else if($syntax[o].indexOf("#[else")==0){
			var $code = $syntax[o].replace("#[else ","").replace("]#","");
			$js+="}else{\n";
		}else if($syntax[o].indexOf("#[for")==0){
			var $code = $syntax[o].replace("#[for ","").replace("]#","");
			var array = $code.split(":");
			var vName = array.length>2 ? array[2] : ($variable.charAt($iv++)+$iv);
			$js+="var "+vName+"=-1;for(var o in "+array[1]+" ){\n"+vName+"++;\nvar "+array[0]+" = "+array[1]+"[o];\n";
		}else if($syntax[o].indexOf("#[set")==0){
			var $code = $syntax[o].replace("#[set ","").replace("]#","");
			$js+="window."+$code+";\n";
		}else{
			var $code = $syntax[o].replace("#[","").replace("]#","");
			if($code.indexOf("?")>-1)
				$js +="$returnHtml+=("+$code+");\n";
			else
				$js +="$returnHtml+=empty("+$code+")?\"\":("+$code+");\n";	
		}	
	}
	$js+="}";
	eval($js);		
	return $returnHtml;
}