package com.example.Thuctap.demo.Spring.DTO.Employee;

import com.example.Thuctap.demo.Spring.Annotation.ValidProvince;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeUpdateDto {
    @Length(min = 6 , max = 10 , message = "Độ dài code phải từ 6 - 10 ")
    @NotBlank(message = "Code không được để trống")
    @Pattern(regexp = "^[0-9]*$" ,message = "Code không được chứa chữ cái ")
    private String code ;
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+" , message = "Name không được chứa kí tự số ")
    @NotBlank(message = " Name không được để trống")
    private String name ;
    @Email(message = "Sai định dạng email")
    @NotBlank(message = "Email không được để trống")
    private String email;
    @Length(min = 7 , max = 11 , message = "Số điện thoạt phải từ 7 - 11")
    @Pattern(regexp = "^[0-9]*$" ,message = "Số điện thoại không được chứa chữ cái ")
    @NotBlank(message = "Số điện thoại không được để trống ")
    private String phone ;
    @Positive(message = "Tuổi không được âm ")
    private Integer age ;
    //@ValidProvince(message = "Province không đã tồn tại")
    private Long provinceId ;
    private Long districtId ;
    private Long communeId ;

}
