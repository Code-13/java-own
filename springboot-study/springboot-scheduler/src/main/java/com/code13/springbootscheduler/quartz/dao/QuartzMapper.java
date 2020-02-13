package com.code13.springbootscheduler.quartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.code13.springbootscheduler.quartz.entity.Task;
import org.apache.ibatis.annotations.Mapper;

/**
 * quartz
 *
 * @author code13
 * @date 2020-02-11 22:11
 */
@Mapper
public interface QuartzMapper extends BaseMapper<Task> {

}
