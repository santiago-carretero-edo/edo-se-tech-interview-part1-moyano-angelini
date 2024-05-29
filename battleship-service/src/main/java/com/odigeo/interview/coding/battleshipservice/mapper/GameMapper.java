package com.odigeo.interview.coding.battleshipservice.mapper;

import com.odigeo.interview.coding.battleshipservice.model.Game;
import com.odigeo.interview.coding.battleshipservice.repository.entity.GameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameMapper {

    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    GameEntity map(Game source);
    Game map(GameEntity source);


}
