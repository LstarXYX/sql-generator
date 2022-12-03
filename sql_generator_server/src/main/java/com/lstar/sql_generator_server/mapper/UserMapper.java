package com.lstar.sql_generator_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lstar.sql_generator_server.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>
{
}
