package com.github.code13.springbootdatasource.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 云客应用管理员表
 * </p>
 *
 * @author code13
 * @since 2020-02-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yun_user")
public class YunUser extends Model<YunUser> {

  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  @TableId(value = "user_id", type = IdType.ASSIGN_ID)
  private Long userId;

  /**
   * 头像  应该改为id
   */
  @TableField("avatar")
  private String avatar;

  /**
   * 微信二维码图片
   */
  @TableField("wxcode")
  private String wxcode;

  /**
   * 账号
   */
  @TableField("account")
  private String account;

  /**
   * 密码
   */
  @TableField("password")
  private String password;

  /**
   * md5密码盐
   */
  @TableField("salt")
  private String salt;

  /**
   * 名字
   */
  @TableField("name")
  private String name;

  /**
   * 生日
   */
  @TableField("birthday")
  private LocalDateTime birthday;

  /**
   * 性别(字典)
   */
  @TableField("sex")
  private String sex;

  /**
   * 电子邮件
   */
  @TableField("email")
  private String email;

  /**
   * 电话
   */
  @TableField("phone")
  private String phone;

  /**
   * 角色id(多个逗号隔开)
   */
  @TableField("role_id")
  private String roleId;

  /**
   * 部门id
   */
  @TableField("dept_id")
  private Long deptId;

  /**
   * 状态(字典)
   */
  @TableField("status")
  private String status;

  /**
   * 公司id
   */
  @TableField("company_id")
  private Long companyId;

  /**
   * 类型：0员工，1公司管理员
   */
  @TableField("type")
  private Integer type;

  /**
   * 创建时间
   */
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  /**
   * 创建人
   */
  @TableField(value = "create_user", fill = FieldFill.INSERT)
  private Long createUser;

  /**
   * 更新时间
   */
  @TableField(value = "update_time", fill = FieldFill.UPDATE)
  private LocalDateTime updateTime;

  /**
   * 更新人
   */
  @TableField(value = "update_user", fill = FieldFill.UPDATE)
  private Long updateUser;

  /**
   * 乐观锁
   */
  @TableField("version")
  @Version
  private Integer version;

  @Override
  protected Serializable pkVal() {
    return super.pkVal();
  }

}
