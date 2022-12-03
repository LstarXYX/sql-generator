package com.lstar.sql_generator_server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lstar.sql_generator_server.mapper.ReportMapper;
import com.lstar.sql_generator_server.model.Report;
import com.lstar.sql_generator_server.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService
{
}
