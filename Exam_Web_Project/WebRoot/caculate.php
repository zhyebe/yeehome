<?php
		function calculate($inMessage){
		$simplexml = new SimpleXMLElement($inMessage->str);
		$operand1 = $simplexml->param1[0];
		$operand2 = $simplexml->param2[0];
		$operation = $simplexml->param3[0];
		if($operation != null)
		{
		  switch($operation)
		  {
		  case "add" : $result= $operand1 + $operand2; break;
		  case "sub" : $result= $operand1 - $operand2; break;
		  case "mul" : $result= $operand1 * $operand2; break;
		  case "div" : $result= $operand1 / $operand2; break;
		  }
		}

		$response = <<<XML
		  <result>$result</result>
		XML;
		$returnMsg = new WSMessage($response);
		return $returnMsg;
		}

		$service = new WSService(array("operations" => array("calculate")));
		$service->reply();
		?>