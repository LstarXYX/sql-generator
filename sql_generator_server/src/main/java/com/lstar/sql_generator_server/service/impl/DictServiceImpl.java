package com.lstar.sql_generator_server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lstar.sql_generator_server.mapper.DictMapper;
import com.lstar.sql_generator_server.model.Dict;
import com.lstar.sql_generator_server.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService
{

}
