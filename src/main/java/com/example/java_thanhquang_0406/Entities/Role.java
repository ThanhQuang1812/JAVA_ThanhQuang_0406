package com.example.java_thanhquang_0406.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.NotFound;
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    @NotBlank(message ="Tên là bắt buộc")
    private String role_name;


}
