package com.janqa.reactive.mapper;

import com.janqa.reactive.model.Req;
import com.janqa.reactive.model.ReqDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ReqMapper {
    ReqDTO map(Req req);
}
