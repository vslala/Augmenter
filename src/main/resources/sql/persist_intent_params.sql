INSERT INTO `intent_parameters`(
			`intent_name`, 
			`param_db_col_name`, 
			`param_query_placeholder_name`, 
			`param_valid_regex`,
			`is_required`,
			`param_type`) 
VALUES  (
	:intentName, 
	:dbColName,  
	:queryPlaceholderName, 
	:validRegex, 
	:isRequired, 
	:paramType)