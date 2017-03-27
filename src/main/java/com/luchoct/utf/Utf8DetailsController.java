package com.luchoct.utf;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/utf8Details", description = "returns details of an utf8 character", basePath = "/utf8Details")
@RestController
@RequestMapping("/utf8Details")
public class Utf8DetailsController {

    @Autowired
    private Utf8DetailsService service;

    @ApiOperation(value = "Returns utf8 details of given an utf8 character",
            notes = "One single character is accepted",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of utf8 detail", response = Utf8Details.class),
            @ApiResponse(code = 400, message = "Wrong utf8 character"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(method = RequestMethod.GET, value = "/{utf8Character}")
    public Utf8Details getUtfDetails(
            @ApiParam(value = "utf8 character for which method should retrieve details", required = true)
            @PathVariable final String utf8Character) throws WrongUtf8CharacterException {
        return service.getDetails(utf8Character);
    }
}