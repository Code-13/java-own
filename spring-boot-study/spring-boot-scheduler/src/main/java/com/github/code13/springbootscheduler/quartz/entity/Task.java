package com.github.code13.springbootscheduler.quartz.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.github.code13.springbootscheduler.quartz.entity.enums.JobStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 任务
 *
 * @author code13
 * @date 2020-02-11 22:16
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Accessors(chain = true)
@TableName("sys_task")
public class Task implements Serializable {
  private static final long serialVersionUID = 2523145534192258398L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;
  /**
   * 任务名
   */
  @TableField(value = "job_name")
  private String jobName;

  /**
   * 任务描述
   */
  @TableField("description")
  private String description;

  /**
   * cron表达式
   */
  @TableField("cron_expression")
  private String cronExpression;


  /**
   * 任务执行时调用哪个类的方法 包名+类名
   */
  @TableField("bean_class")
  private String beanClass;

  /**
   * 任务状态
   */
  @TableField("job_status")
  private JobStatusEnum jobStatus;

  /**
   * 任务分组
   */
  @TableField("job_group")
  private String jobGroup;

  @TableField(value = "create_user", fill = FieldFill.INSERT)
  private String createUser;

  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @TableField(value = "update_user", fill = FieldFill.UPDATE)
  private String updateUser;

  @TableField(value = "update_time", fill = FieldFill.UPDATE)
  private LocalDateTime updateTime;


}
