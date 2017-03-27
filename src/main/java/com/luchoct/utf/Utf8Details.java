package com.luchoct.utf;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@ApiModel(value="Utf8Details", description="Details of an utf8 character")
@Value
public class Utf8Details {

    @ApiModelProperty(position = 1, required = true, value = "unicode value of utf8 character")
	@JsonProperty("unicode")
	private final String unicodeValue;

    @ApiModelProperty(position = 2, required = true, value = "decimal value of utf8 character")
	@JsonProperty("dec")
	private final String decimalValue;

    @ApiModelProperty(position = 3, required = true, value = "hexadecimal value of utf8 character")
	@JsonProperty("hex")
	private final String hexadecimalValue;
}
