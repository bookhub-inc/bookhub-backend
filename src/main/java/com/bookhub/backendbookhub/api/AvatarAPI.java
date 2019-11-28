package com.bookhub.backendbookhub.api;

import com.bookhub.backendbookhub.dao.AvatarDAO;
import com.bookhub.backendbookhub.entity.AvatarEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Api(value="Avatar",tags = "Avatar")
public class AvatarAPI {

    @Autowired
    private AvatarDAO avatarDAO;

    @ResponseStatus(OK)
    @ApiOperation(value = "Busca avatares",notes = "Retorna todos os avatares, id/url", response = List.class)
    @GetMapping("/avatar")
    public ResponseEntity<List<AvatarEntity>> findAll() {
        return new ResponseEntity<>(avatarDAO.findAll(),OK);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Busca avatare por ID",notes = "Retorna id/url de um avatar especifico", response = List.class)
    @GetMapping("/avatar/{idAvatar}")
    public ResponseEntity<AvatarEntity> find(@ApiParam(example = "1",required = true) @PathVariable("idAvatar") final Integer idAvatar) {
        return new ResponseEntity<>(avatarDAO.find(idAvatar),OK);
    }


}
