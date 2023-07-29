package com.example.Thuctap.demo.Spring.DTO.Employee;

import com.example.Thuctap.demo.Spring.DTO.Degree.Degree1DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
// class trả về danh sách có chứa văn bằng
public class Employee1DTO {
    private Long id;
    @Length(min = 6 , max = 10 , message = "Độ dài code phải từ 6 - 10 ")
    @NotBlank(message = "Code không được để trống")
    @Pattern(regexp = "^[0-9]*$" ,message = "Code không được chứa chữ cái ")
    private String code;
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+" , message = "Name không được chứa kí tự số ")
    @NotBlank(message = " Name Không được để trống")
    private String name ;
    @Positive(message = "Tuổi không được âm ")
    private Integer age;
    @Email(message = "Sai định dạng email")
    @NotBlank(message = "Email không được để trống")
    private String email;
    @Length(min = 7 , max = 11 , message = "Số điện thoạt phải từ 7 - 11")
    @Pattern(regexp = "^[0-9]*$" ,message = "Số điện thoại không được chứa chữ cái ")
    @NotBlank(message = "Số điện thoại không được để trống ")
    private String phone ;
    private Long provinceId;
    private Long districtId;
    private Long communeId;
    private List<Degree1DTO> degreeList;
}
