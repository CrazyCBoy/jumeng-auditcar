package com.jumeng.auditcar.web.form;

import com.jumeng.auditcar.domain.UserInfo;
import com.jumeng.auditcar.domain.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ClassName UserForm
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/12 9:55
 * @Version 1.0
 **/
@ApiModel(description = "用户实体")
@Data
public class UserForm extends BaseForm<UserInfo> {

    @NotBlank(message = "用户手机号不能为空")
    @ApiModelProperty(value = "手机号", example="13555555555", required=true)
    private String mobile;// 手机号

    @NotBlank(message = "用户姓名不能为空")
    @ApiModelProperty(value = "姓名", example="审车用户A", required=false)
    private String name;//姓名

    @ApiModelProperty(value = "身份证号", example="41011119XXXXXXXXXX2", required=false)
    private String cardNum;//身份证号

    @ApiModelProperty(value = "生日", example="2019-09-02", required=false)
    private Date birthday;//生日

    @ApiModelProperty(value = "地址", example="XXXX文化路任寨北街", required=false)
    private String address;//地址

    @ApiModelProperty(value = "邮箱", example="370551444@qq.com", required=false)
    private String email;//邮箱

    @ApiModelProperty(value = "省", example="河南省", required=false)
    private String province;//省

    @ApiModelProperty(value = "市", example="郑州市", required=false)
    private String city;//市

    @ApiModelProperty(value = "区/县", example="金水区", required=false)
    private String county;//县级市

    @ApiModelProperty(value = "性别 F：女性；M：男性", example="F", required=false)
    private String memberSex;//性别 F：女性；M：男性

    @ApiModelProperty(value = "年龄", example="25", required=false)
    private Integer age;//年龄

    @ApiModelProperty(value = "头像", example="/upload/ddd/1111.jpg", required=false)
    private String avatar;//头像

    @ApiModelProperty(value = "昵称", example="审车用户昵称", required=false)
    private String nickName;//昵称

    @ApiModelProperty(value = "密码", example="123456", required=false)
    private String password;// 密码
}
